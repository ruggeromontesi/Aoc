package it.ruggero.adventofcode2021.day15.part1;

import it.ruggero.adventofcode2021.day15.common.readfile.ParseFile;
import it.ruggero.adventofcode2021.day15.part2.MatrixMapping;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChitonContext {

    @Getter
    private static int WIDTH;
    @Getter
    private static int HEIGHT;

    @Getter
    private static int[][] cavernMap;
    @Getter
    private static Node[][] nodeMap;

    @Getter
    private static int[][] extendedCavernMap;
    @Getter
    private static Node[][] extendedNodeMap;
    private static final Set<Direction> directionsToSearch = Set.of(Direction.values());

    private static final Set<Node> unvisitedNodes = new TreeSet<>(Comparator.comparingInt(Node::getRiskLevel).thenComparing(Node::getCoordinate));

    public static void buildFromFile(String filePath) {
        List<String> lines = (new ParseFile(filePath)).getLines();

        WIDTH = lines.get(0).length();
        HEIGHT = lines.size();
        cavernMap = new int[HEIGHT][WIDTH];
        nodeMap = new Node[HEIGHT][WIDTH];

        for (int rowNumber = 0; rowNumber < HEIGHT; rowNumber++) {
            for (int colNumber = 0; colNumber < WIDTH; colNumber++) {
                cavernMap[rowNumber][colNumber] = Integer.parseInt(lines.get(rowNumber).substring(colNumber, colNumber + 1));
                var node = Node.builder()
                        .riskLevel(Integer.MAX_VALUE)
                        .coordinate(new Coordinate(rowNumber, colNumber))
                        .visited(false)
                        .build();
                nodeMap[rowNumber][colNumber] = node;
                unvisitedNodes.add(node);
            }
        }
    }


    public static void buildFromFileExtended(String filePath) {
        buildFromFile(filePath);
        extendedCavernMap = MatrixMapping.extendMatrix(cavernMap);
        extendedNodeMap = new Node[5*HEIGHT][5*WIDTH];
        unvisitedNodes.clear();
        for (int rowNumber = 0; rowNumber < 5*HEIGHT; rowNumber++) {
            for (int colNumber = 0; colNumber < 5*WIDTH; colNumber++) {
                var node = Node.builder()
                        .riskLevel(Integer.MAX_VALUE)
                        .coordinate(new Coordinate(rowNumber, colNumber))
                        .visited(false)
                        .build();
                extendedNodeMap[rowNumber][colNumber] = node;
                unvisitedNodes.add(node);
            }
        }
        nodeMap = extendedNodeMap;
        cavernMap = extendedCavernMap;
        HEIGHT =  5*HEIGHT;
        WIDTH = 5*WIDTH;
    }

    final static Consumer<Node> processSingleNode = node -> {
        var neighbours = findNeighbours(node.getCoordinate());
        var riskLevelOfThisNode = node.getRiskLevel();
        Map<Coordinate,Integer> riskLevelOfNeighbours = evaluateRiskLevelOfNeighbours(neighbours, riskLevelOfThisNode);
        updateRiskLevelOfNeighboursIfLower(riskLevelOfNeighbours,node.getCoordinate());
        node.setVisited(true);
        unvisitedNodes.removeIf(Node::isVisited);

    };

    private static void updateRiskLevelOfNeighboursIfLower(Map<Coordinate, Integer> riskLevelOfNeighbours, Coordinate coordinateOfCurrentNode) {
        riskLevelOfNeighbours.forEach((neighbourCoordinate, value) -> {
            if (nodeMap[neighbourCoordinate.getRow()][neighbourCoordinate.getCol()].getRiskLevel() > value) {
                nodeMap[neighbourCoordinate.getRow()][neighbourCoordinate.getCol()].setRiskLevel(value);
                nodeMap[neighbourCoordinate.getRow()][neighbourCoordinate.getCol()].setCoordinateOfPreviousNode(coordinateOfCurrentNode);
            }
        });
    }

    private static Map<Coordinate, Integer> evaluateRiskLevelOfNeighbours(List<Coordinate> neighbours,int riskLevelOfThisNode) {
        return  neighbours.stream().collect(Collectors.toMap(
                Function.identity(),
                n -> riskLevelOfThisNode + cavernMap[n.getRow()][n.getCol()]
        ));
    }

    private static List<Coordinate> findNeighbours(Coordinate coordinate) {
        return directionsToSearch.stream().map(direction -> direction.findNeighbour(coordinate))
                .filter(Optional::isPresent).flatMap(Optional::stream).collect(Collectors.toList());
    }

    public static int mainRun() {
        preliminary();
        Set<Node> unvisitedNodesWithMinimumRiskLevel = findUnvisitedNodesWithMinimumRiskLevel();
        int i = 0;
        do {
            unvisitedNodesWithMinimumRiskLevel.forEach(processSingleNode);
            unvisitedNodesWithMinimumRiskLevel = findUnvisitedNodesWithMinimumRiskLevel();
            if(i%100 == 0 ) {
                System.out.println("step no " + i + " timestamp " + LocalTime.now()+ "  progress " + calculateProgress() + "% ");

            }
            i++;
        }
        while(!unvisitedNodesWithMinimumRiskLevel.isEmpty());
        return nodeMap[nodeMap.length - 1][nodeMap[0].length - 1].getRiskLevel();
    }

    private static double calculateProgress() {
        double progress ;

         progress = 100* (double)((HEIGHT*WIDTH) - unvisitedNodes.size())/(HEIGHT*WIDTH);

         progress = progress - 10 * progress % 10;


        return progress;
    }

    public static int mainRunExtended(String filePath) {
        Instant now = Instant.now();


        int out;
        buildFromFileExtended(filePath);
        out = mainRun();
        Instant later = Instant.now();
        Duration duration  = Duration.between(now,later);
        System.out.println("duration " + duration.toSeconds());

        return out;
    }

    private static void preliminary() {
        nodeMap[0][0].setRiskLevel(0);
    }

    private static Set<Node> findUnvisitedNodesWithMinimumRiskLevel() {
        final Set<Node> unvisitedNodesWithMinimumRiskLevel = new HashSet<>();
        unvisitedNodes.stream().min(Comparator.comparingInt(Node::getRiskLevel)).ifPresent(node -> {
                    var unvisitedNodesWithMinimumRiskLevelSet= unvisitedNodes.stream()
                            .filter(n2 -> Objects.equals(n2.getRiskLevel(), node.getRiskLevel()))
                            .collect(Collectors.toSet());
                    unvisitedNodesWithMinimumRiskLevel.addAll(unvisitedNodesWithMinimumRiskLevelSet);
        });
        return unvisitedNodesWithMinimumRiskLevel ;
    }

    @Data
    @RequiredArgsConstructor
    @Builder
    public static class Coordinate implements Comparable<Coordinate> {

        private int row;
        private int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Coordinate o) {
            return (this.row - o.row) != 0 ? (this.row - o.row) : (this.col - o.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (row != that.row) return false;
            return col == that.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    @Data
    @With
    @Builder
    public static class Node {
        private int riskLevel;
        private Coordinate coordinate;
        boolean visited;
        private Coordinate coordinateOfPreviousNode;
    }

    public enum Direction {
        NORTH {
            @Override
            public Optional<Coordinate> findNeighbour(ChitonContext.Coordinate thisCoordinate) {
                if (thisCoordinate.getRow() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() - 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            }


        },
        EAST {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getCol() < WIDTH - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() + 1));
                } else {
                    return Optional.empty();
                }
            }

        },
        SOUTH {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getRow() < HEIGHT - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() + 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            }

        },
        WEST {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getCol() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() - 1));
                } else {
                    return Optional.empty();
                }
            }

        };

        public abstract Optional<Coordinate> findNeighbour(ChitonContext.Coordinate startCoordinate);


    }

}
