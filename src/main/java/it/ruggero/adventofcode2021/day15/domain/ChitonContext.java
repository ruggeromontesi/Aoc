package it.ruggero.adventofcode2021.day15.domain;

import it.ruggero.adventofcode2021.day15.readfile.ParseFile;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
public class ChitonContext {
    @Getter
    private static int WIDTH;
    @Getter
    private static int HEIGHT;

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

    public static Direction findDirectionsWithLowestRisk(Coordinate currentCoordinate) {
        int minRisk = 0;

        Map<Direction,Integer> directionRiskMap = new HashMap<>();
        Arrays.asList(Direction.values()).forEach(direction -> {
            int risk = 0;
            direction.findNeighbour(currentCoordinate).ifPresent(coordinate -> {
                var riskAtThisCoordinate = cavernMap.get(coordinate);
                if (riskAtThisCoordinate != null && riskAtThisCoordinate <= minRisk ) {
                    directionRiskMap.put(direction, riskAtThisCoordinate);
                }
            });


        });

        if (directionRiskMap.size() > 1) {
            return null;
        } else if (directionRiskMap.size() > 0) {
            return directionRiskMap.keySet().stream().findAny().orElseThrow();

        }

        return null;

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
                return SOUTH;
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

        public abstract  Direction opposite();
    }
}


