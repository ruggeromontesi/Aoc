package it.ruggero.adventofcode2022.Day10.operations;

public interface GenericOperation {
    boolean isExecutionCompleted();
    void runOneCycle(int clockCycle);

    int getResult(int registerValue);
}
