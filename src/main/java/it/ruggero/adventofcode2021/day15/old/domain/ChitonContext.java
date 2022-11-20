package it.ruggero.adventofcode2021.day15.old.domain;

import it.ruggero.adventofcode2021.day15.old.readfile.ParseFile;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ChitonContext {

    //Dijkstra's algorithm
    @Getter
    private static int WIDTH;
    @Getter
    private static int HEIGHT;

    private Integer a;


    private static List<Coordinate> coordinates = new ArrayList<>();



    @Getter
    private static final Map<Coordinate, Integer> cavernMap = new TreeMap<>();
    private ChitonContext.Coordinate currentCordinate = new ChitonContext.Coordinate(0, 0);

    public static void buildFromFile(String filePath) {
        List<String> lines = (new ParseFile(filePath)).getLines();

        WIDTH = lines.get(0).length();
        HEIGHT = lines.size();
        for (int rowNumber = 0; rowNumber < HEIGHT; rowNumber++) {
            for (int colNumber = 0; colNumber < WIDTH; colNumber++) {
                cavernMap.put(new Coordinate(rowNumber, colNumber), Integer.parseInt(lines.get(rowNumber).substring(colNumber, colNumber + 1)));
            }
        }

    }

    @Data
    @RequiredArgsConstructor
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

    public static void printRiskMap() {
        for (int rowNumber = 0; rowNumber < HEIGHT; rowNumber++) {
            for (int colNumber = 0; colNumber < WIDTH; colNumber++) {
                System.out.print("[" + cavernMap.get(new Coordinate(rowNumber, colNumber)) + "]");
            }
            System.out.print("\n");
        }
    }

    public void move(Direction direction) {
        currentCordinate = move(currentCordinate, direction);

    }

    public Coordinate move(Coordinate startPoint, Direction direction) {

        Coordinate endPoint = new Coordinate(startPoint.getRow(), startPoint.getCol());


        switch (direction) {
            case EAST:
                if (startPoint.getCol() < WIDTH - 1) {
                    endPoint.setCol(startPoint.getCol() + 1);
                }
                return endPoint;

            case WEST:
                if (startPoint.getCol() > 0) {
                    endPoint.setCol(startPoint.getCol() - 1);
                }
                return endPoint;

            case NORTH:
                if (startPoint.getRow() > 0) {
                    endPoint.setRow(startPoint.getRow() - 1);
                }
                return endPoint;

            case SOUTH:
                if (startPoint.getRow() < HEIGHT - 1) {
                    endPoint.setRow(startPoint.getRow() + 1);
                }
                return endPoint;
        }

        return endPoint;
    }


    public static Map<Direction, Integer> findDirectionsWithLowestRisk(Coordinate currentCoordinate) {
        return findDirectionsWithLowestRiskExcludeDirection(currentCoordinate, null);
    }

    public static Map<Direction, Integer> findDirectionsWithLowestRiskExcludeDirection(Coordinate currentCoordinate, Direction directionToBeExcluded) {

        Map<Direction, Integer> directionRiskMap = new HashMap<>();
        Arrays.stream(Direction.values())
                .filter(direction -> direction == Direction.SOUTH || direction == Direction.EAST)
                .forEach(direction -> {
                    direction.findNeighbour(currentCoordinate).ifPresent(coordinate -> {
                        directionRiskMap.put(direction, cavernMap.get(coordinate));
                    });
                });

        return directionRiskMap;
    }

    public static Direction findDirectionWithLowestRisk(Coordinate currentCoordinate) {
        Map<Direction, Integer> directionsRiskAtFirstStep = findDirectionsWithLowestRisk(currentCoordinate);

        Map<Direction,Map<Direction, Integer>> directionRiskMapSecondStep = new HashMap<>();

        directionsRiskAtFirstStep.keySet()
                .stream()
                .filter(direction -> direction == Direction.SOUTH || direction == Direction.EAST)
                .forEach(direction -> {
            var startingPointOfSecondStep = direction.findNeighbour(currentCoordinate).orElseThrow();
            var directionsWithLowestRisk = findDirectionsWithLowestRiskExcludeDirection(startingPointOfSecondStep, direction.opposite());
            directionRiskMapSecondStep.put(direction,directionsWithLowestRisk);
        });

        var directionRiskMapAtSecondStep = directionRiskMapSecondStep
                .entrySet().stream().collect(
                        Collectors.toMap(Map.Entry::getKey, e -> e.getValue().values().stream().mapToInt(Integer::intValue).min().orElse(0) + directionsRiskAtFirstStep.get(e.getKey())) );

        int min = directionRiskMapAtSecondStep.values().stream().mapToInt(Integer::intValue).min().orElse(0);

        directionRiskMapAtSecondStep.entrySet().removeIf(e -> e.getValue() > min);


        return  directionRiskMapAtSecondStep.keySet().stream().findFirst().orElse(Direction.EAST);
    }

    public static int go() {
        boolean stop = false;
        int count = 0;

        int totalRisk= 0;

        var currentCoordinate = new Coordinate(0,0);


        while(!stop && count < 2*(HEIGHT + WIDTH)) {
            Direction direction = findDirectionWithLowestRisk(currentCoordinate);
            var nextCoordinate = direction.findNeighbour(currentCoordinate).orElse(currentCoordinate);
            if(!nextCoordinate.equals(currentCoordinate)) {
                totalRisk += cavernMap.get(nextCoordinate);
                currentCoordinate = nextCoordinate;
                count++;
                coordinates.add(currentCoordinate);
            } else {
                stop = true;
            }
        }

        return  totalRisk;
    }


    public enum Direction {
        NORTH {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getRow() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() - 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            }

            @Override
            public Direction opposite() {
                return SOUTH;
            }

            ;
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

            @Override
            public Direction opposite() {
                return WEST;
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

            @Override
            public Direction opposite() {
                return NORTH;
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

            @Override
            public Direction opposite() {
                return EAST;
            }
        };

        public abstract Optional<Coordinate> findNeighbour(Coordinate startCoordinate);

        public abstract Direction opposite();
    }
}


