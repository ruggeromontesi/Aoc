package it.ruggero.adventofcode2021.day15.domain;

import it.ruggero.adventofcode2021.day15.readfile.ParseFile;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ChitonContext {
    @Getter
    private static int WIDTH;
    @Getter
    private static int HEIGHT;


    private static List<Coordinate> coordinates = new ArrayList<>();


    @Setter
    private static Direction directionOfThePreviousStep = null;

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

    public Direction chooseDirection(Coordinate currentCoordinate) {

        Map<Direction, Integer> directionRiskMap = new TreeMap<>();

        for (Direction direction : Direction.values()) {
            var a = move(currentCoordinate, direction);
            if (!a.equals(currentCoordinate)) {
                directionRiskMap.put(direction, cavernMap.get(a));
            }

        }

        return directionRiskMap.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).orElseThrow();


    }

//    public static Map<Direction, Integer> findDirectionsWithLowestRisk(Coordinate currentCoordinate) {
//        final int[] minRisk = {10};
//
//        Map<Direction, Integer> directionRiskMap = new HashMap<>();
//        Arrays.asList(Direction.values()).forEach(direction -> {
//            direction.findNeighbour(currentCoordinate).ifPresent(coordinate -> {
//                var riskAtThisCoordinate = cavernMap.get(coordinate);
//                if (riskAtThisCoordinate != null && riskAtThisCoordinate <= minRisk[0]) {
//                    directionRiskMap.put(direction, riskAtThisCoordinate);
//                    if(riskAtThisCoordinate < minRisk[0]) {
//                        minRisk[0] = riskAtThisCoordinate;
//                    }
//                }
//            });
//        });
//
//        directionRiskMap.entrySet().removeIf(e -> e.getValue() > minRisk[0]);
//        return directionRiskMap;
//    }


    public static Map<Direction, Integer> findDirectionsWithLowestRisk(Coordinate currentCoordinate) {
        return findDirectionsWithLowestRiskExcludeDirection(currentCoordinate, directionOfThePreviousStep == null ? null : directionOfThePreviousStep.opposite());
    }

    public static Map<Direction, Integer> findDirectionsWithLowestRiskExcludeDirection(Coordinate currentCoordinate, Direction directionToBeExcluded) {
        final int[] minRisk = {10};

        Map<Direction, Integer> directionRiskMap = new HashMap<>();
        Arrays.stream(Direction.values()).filter(direction ->
                        (!direction.equals(directionToBeExcluded))
            ).filter(direction -> direction == Direction.SOUTH || direction == Direction.EAST)
                .forEach(direction -> {
                    direction.findNeighbour(currentCoordinate).ifPresent(coordinate -> {
                        var riskAtThisCoordinate = cavernMap.get(coordinate);
                        if (riskAtThisCoordinate != null && riskAtThisCoordinate <= minRisk[0]) {
                            directionRiskMap.put(direction, riskAtThisCoordinate);
                            if (riskAtThisCoordinate < minRisk[0]) {
                                minRisk[0] = riskAtThisCoordinate;
                            }
                        }
                    });
                });

        directionRiskMap.entrySet().removeIf(e -> e.getValue() > minRisk[0]);
        return directionRiskMap;
    }

    public static Direction findDirectionWithLowestRisk(Coordinate currentCoordinate) {
        Map<Direction, Integer> directionsWithLowestRiskAtFirstStep = findDirectionsWithLowestRisk(currentCoordinate);
        if (directionsWithLowestRiskAtFirstStep.size() == 1) {
            return  directionsWithLowestRiskAtFirstStep.keySet().stream().findFirst().orElseThrow();
        }

        Map<Direction,Map<Direction, Integer>> directionRiskMapSecondStep = new HashMap<>();

        directionsWithLowestRiskAtFirstStep.keySet().forEach(direction -> {
            var startingPointOfSecondStep = direction.findNeighbour(currentCoordinate).orElseThrow();
            var directionsWithLowestRisk = findDirectionsWithLowestRiskExcludeDirection(startingPointOfSecondStep, direction.opposite());
            directionsWithLowestRisk.entrySet().removeIf(e -> e.getKey().equals(direction.opposite()));
            directionRiskMapSecondStep.put(direction,directionsWithLowestRisk);
        });

        var directionRiskMapAtSecondStep = directionRiskMapSecondStep
                .entrySet().stream().collect(
                        Collectors.toMap(Map.Entry::getKey, e -> e.getValue().values().stream().mapToInt(Integer::intValue).sum()));

        int min = directionRiskMapAtSecondStep.values().stream().mapToInt(Integer::intValue).min().orElse(-1);

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
                directionOfThePreviousStep = direction;
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


