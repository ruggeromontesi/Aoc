package it.ruggero.adventofcode2021.day5.streamsolution.entity;

public class Coordinate implements Comparable<Coordinate> {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(String fileString) {
        String[] coordinatesAsString = fileString.split(",");
        if (coordinatesAsString.length != 2) {
            throw new RuntimeException("Invalid string as input to create coordinate");
        }
        this.x = Integer.parseInt(coordinatesAsString[0].trim());
        this.y = Integer.parseInt(coordinatesAsString[1].trim());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if(! (o instanceof Coordinate)) {
            return false;
        } else {
            Coordinate that = (Coordinate) o;

            return this.x == that.getX() && this.y == that.getY();
        }

    }

    @Override
    public int hashCode() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Coordinate other) {
        return (this.x != other.x) ? (this.x - other.x) : (this.y - other.y);
    }
}
