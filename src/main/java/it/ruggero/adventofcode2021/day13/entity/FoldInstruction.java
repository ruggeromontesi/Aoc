package it.ruggero.adventofcode2021.day13.entity;


public class FoldInstruction {

    private final Direction direction;

    private final int value;

    public FoldInstruction(Direction direction, int value) {
        this.direction = direction;
        this.value = value;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getValue() {
        return value;
    }

    public enum Direction {
        x,y
    }
}
