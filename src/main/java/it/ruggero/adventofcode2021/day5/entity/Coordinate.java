/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day5.entity;

/**
 * @author montesr
 */
public class Coordinate {
    private int x ;
    private int y;

    public Coordinate(String string) {
        String[] values = string.split(",");
        this.x = Integer.parseInt(values[0]);
        this.y = Integer.parseInt(values[1]);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (getX() != that.getX()) return false;
        return getY() == that.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }
}
