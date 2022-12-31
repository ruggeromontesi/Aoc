package it.ruggero.adventofcode2022.Day10.operations;

public class Add extends AbstractOperation {
    private int valueToAdd;

    public Add(String instr) {
        valueToAdd = Integer.parseInt(instr.split(" ")[1]);
        requiredClockCycles = 2;
    }

    @Override
    public int getResult(int registerValue) {
        return registerValue + valueToAdd;
    }
}
