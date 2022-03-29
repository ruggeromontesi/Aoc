package it.ruggero.adventofcode2021.day13.entity;

import java.util.Comparator;

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
        return (this.y != o.y) ? this.y - o.y : this.x - o.x;
    }
}
