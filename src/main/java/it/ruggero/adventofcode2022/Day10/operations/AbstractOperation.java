package it.ruggero.adventofcode2022.Day10.operations;

public abstract class AbstractOperation implements GenericOperation{
    protected int clockCyclesOperationIsRun = -1;
    protected int requiredClockCycles = -2;

    @Override
    public boolean isExecutionCompleted() {
        return clockCyclesOperationIsRun == requiredClockCycles;
    }

    @Override
    public void runOneCycle(int clockCycle) {
        if(clockCyclesOperationIsRun == -1 ) {
            clockCyclesOperationIsRun = 1;
        } else {
            clockCyclesOperationIsRun ++;
        }
    }
}
