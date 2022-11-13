package it.ruggero.adventofcode2021.day15;

import java.util.StringJoiner;

public class Position implements Comparable<Position>{

    private final Coordinate coordinate;

    private final int riskLevel;

    public Position(Coordinate coordinate, int iskLevel) {
        this.coordinate = coordinate;
        this.riskLevel = iskLevel;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    @Override
    public int compareTo(Position o) {
        if (this.coordinate.getY() != o.coordinate.getY()) {
            return this.coordinate.getY() - o.coordinate.getY();
        } else {
            return this.coordinate.getX() - o.coordinate.getX();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (riskLevel != position.riskLevel) return false;
        return coordinate.equals(position.coordinate);
    }

    @Override
    public int hashCode() {
        int result = coordinate.hashCode();
        result = 31 * result + riskLevel;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                .add("coordinate=" + coordinate)
                .add("riskLevel=" + riskLevel)
                .toString();
    }
}
