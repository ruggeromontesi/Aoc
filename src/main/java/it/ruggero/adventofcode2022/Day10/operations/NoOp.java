package it.ruggero.adventofcode2022.Day10.operations;

public class NoOp extends AbstractOperation{

    public NoOp() {
        requiredClockCycles = 1;
    }

    @Override
    public int getResult(int registerValue) {
        return registerValue;
    }
}
