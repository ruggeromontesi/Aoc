package it.ruggero.adventofcode2021.day5.streamsolution.entity;

public class Line {
    private final Coordinate initialCoordinate;
    private final Coordinate finalCoordinate;

    private final boolean vertical;

    private final boolean horizontal;

    private final int gradient;

    public Line(Coordinate initialCoordinate, Coordinate finalCoordinate) {
        if (initialCoordinate.compareTo(finalCoordinate) < 0) {
            this.initialCoordinate = initialCoordinate;
            this.finalCoordinate = finalCoordinate;
        } else {
            this.initialCoordinate = finalCoordinate;
            this.finalCoordinate = initialCoordinate;
        }

        if (this.initialCoordinate.getX() == this.finalCoordinate.getX()) {
            this.vertical = true;
            this.horizontal = false;
            gradient = 32000;
            return;
        } else {
            this.vertical = false;
        }

        if (this.initialCoordinate.getY() == this.finalCoordinate.getY()) {
            this.horizontal = true;
            gradient = 0;
            return;
        } else {
            this.horizontal = false;
        }

        gradient = (finalCoordinate.getY() - initialCoordinate.getY()) / (finalCoordinate.getX() - initialCoordinate.getX());

    }

    public Coordinate getInitialCoordinate() {
        return initialCoordinate;
    }

    public Coordinate getFinalCoordinate() {
        return finalCoordinate;
    }

    public boolean isVertical() {
        return vertical;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean containsCoordinate(Coordinate coordinate) {

        if (this.vertical) {
            return coordinate.getY() > this.initialCoordinate.getY() - 1
                    && coordinate.getY() < this.finalCoordinate.getY() + 1
                    && coordinate.getX() == initialCoordinate.getX();
        }
        if (this.horizontal) {
            return coordinate.getX() > this.initialCoordinate.getX() - 1 &&
                    coordinate.getX() < this.finalCoordinate.getX() + 1 &&
                    coordinate.getY() == initialCoordinate.getY();
        }
        return (coordinate.getY() - this.initialCoordinate.getY()) == gradient * (coordinate.getX() - this.initialCoordinate.getX()) &&
                coordinate.getX() > this.initialCoordinate.getX() - 1 &&
                coordinate.getX() < this.finalCoordinate.getX() + 1;
    }

    @Override
    public String toString() {
        return "Line{" +
                "initialCoordinate=" + initialCoordinate +
                ", finalCoordinate=" + finalCoordinate +
                '}';
    }
}
