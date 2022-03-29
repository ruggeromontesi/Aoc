package it.ruggero.adventofcode2021.day13.entity;

public class Dot {
    private final Coordinate coordinate;

    private String symbol;

    public Dot(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Dot(Coordinate coordinate, String symbol) {
        this.coordinate = coordinate;
        this.symbol = symbol;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
