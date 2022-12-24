package it.ruggero.util.core;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.Optional;
import java.util.function.BiFunction;

public class AocMap<T> {

    private T[][] entityMap;

    private final T[][] elements;

    @Getter
    private final int[][] sourceInputMap;

    private final BiFunction<Coordinate, Integer, T> mapper;

    public AocMap(int[][] source, Class<T> clazz, BiFunction<Coordinate, Integer, T> mapper) {
        sourceInputMap = source;
        this.mapper = mapper;
        elements = (T[][]) Array.newInstance(clazz, sourceInputMap.length, sourceInputMap[0].length);
        createEntityMap();
    }

    private void createEntityMap() {
        for (int rowIndex = 0; rowIndex < sourceInputMap.length; rowIndex++) {
            for (int colIndex = 0; colIndex < sourceInputMap[rowIndex].length; colIndex++) {
                Coordinate coordinate = Coordinate.builder().row(rowIndex).col(colIndex).build();
                elements[rowIndex][colIndex] = mapper.apply(coordinate, sourceInputMap[coordinate.getRow()][coordinate.getCol()]);
            }
        }
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

    public Optional<Coordinate> findNeighbourOfCoordinateInDirection(Coordinate thisCoordinate, Directions direction) {
        switch (direction) {
            case NORTH:
                if (thisCoordinate.getRow() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() - 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            case EAST:
                if (thisCoordinate.getCol() < sourceInputMap[thisCoordinate.getRow()].length - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() + 1));
                } else {
                    return Optional.empty();
                }
            case SOUTH:
                if (thisCoordinate.getRow() < sourceInputMap.length - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() + 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            case WEST:
                if (thisCoordinate.getCol() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() - 1));
                } else {
                    return Optional.empty();
                }

        }
        return Optional.empty();

        }



}
