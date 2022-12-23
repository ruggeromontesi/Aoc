package it.ruggero.util.core;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

public class AocMap<T> {

    @Getter
    private int[][] heightMap;

    public AocMap(int[][] source) {
        heightMap = source;
    }


    @Data
    @RequiredArgsConstructor
    @Builder
    public static class Coordinate implements Comparable<AocMap.Coordinate> {
        private int row;
        private int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(AocMap.Coordinate o) {
            return (this.row - o.row) != 0 ? (this.row - o.row) : (this.col - o.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AocMap.Coordinate)) {
                return false;
            }

            AocMap.Coordinate that = (AocMap.Coordinate) o;

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

    //refactor all those methods
    // is not ok to have them in the enum as they can access only static fields

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
        },
        EAST {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getCol() < heightMap[thisCoordinate.getRow()].length - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() + 1));
                } else {
                    return Optional.empty();
                }
            }

        },
        SOUTH {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getRow() < heightMap.length - 1) {
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

        public abstract Optional<Coordinate> findNeighbour(Coordinate startCoordinate);
    }

}
