package it.ruggero.adventofcode2021.day15;

import java.util.StringJoiner;

public class Coordinate implements Comparable<Coordinate> {

    private final int x;

    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.y != o.y) {
            return  this.y - o.y;
        } else {
            return this.x -o.x;
        }

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Coordinate.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }
}
