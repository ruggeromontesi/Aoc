package it.ruggero.adventofcode2022.Day10.operations;

public abstract class AbstractOperation implements GenericOperation{

    private static final String NO_OP = "noop";
    private static final String ADD_OP = "addx";

    protected int clockCyclesOperationIsRun = -1;

    protected int requiredClockCycles = -2;


    public static AbstractOperation parseString(String s){

        if (s.contains(NO_OP)) {
            return new NoOp();
        }

        if(s.contains(ADD_OP)) {
            return new Add(s);
        }


        return null;
    }


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
