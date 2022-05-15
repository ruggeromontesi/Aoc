package it.ruggero.adventofcode2021.day4.streamsolution.entity;

public class Coordinate {
    private final int rowNumber;

    private final int colNumber;

    private final int index;

    public Coordinate(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        index = colNumber + 5*rowNumber;
    }

    public  Coordinate(int index){
        this.colNumber = index%5;
        this.rowNumber = index/5;
        this.index = index;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public int getIndex() {
        return index;
    }
}
