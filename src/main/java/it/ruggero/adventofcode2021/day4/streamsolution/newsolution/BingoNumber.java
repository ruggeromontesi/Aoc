package it.ruggero.adventofcode2021.day4.streamsolution.newsolution;

public class BingoNumber {
    private final int value;

    private boolean drawn;

    public BingoNumber(int value) {

        this.value = value;
        this.drawn = false;
    }

    public int getValue() {
        return value;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public boolean equals(Object other) {
        if(! (other instanceof  BingoNumber)) {
            return  false;
        }

        BingoNumber otherBingoNumber = (BingoNumber) other;

        return this.getValue() == otherBingoNumber.getValue();

    }

    public String toString() {
        return value + "";
    }
}
