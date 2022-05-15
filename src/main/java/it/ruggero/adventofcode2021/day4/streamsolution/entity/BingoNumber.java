package it.ruggero.adventofcode2021.day4.streamsolution.entity;

public class BingoNumber {
    private final int value;

    private boolean drawn;

    public BingoNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }
}
