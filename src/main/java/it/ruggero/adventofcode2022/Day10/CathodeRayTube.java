package it.ruggero.adventofcode2022.Day10;

import it.ruggero.adventofcode2022.Day10.operations.GenericOperation;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.*;

public class CathodeRayTube {
    private Queue<GenericOperation> instructions;
    @Getter
    private int clockCycle = 0;
    @Getter
    private int register = 1;

    private Map<Integer,Integer> signalStrenght = new HashMap<>();

    public CathodeRayTube(List<? extends GenericOperation> instructions) {
        this.instructions = new ArrayDeque<>(instructions);
        signalStrenght.put(20,-1);
        signalStrenght.put(60,-1);
        signalStrenght.put(100,-1);
        signalStrenght.put(140,-1);
        signalStrenght.put(180,-1);
        signalStrenght.put(220,-1);
    }

    public void run() {
        while(!instructions.isEmpty()) {
            var currentInstruction = instructions.poll();
            do {
                currentInstruction.runOneCycle(clockCycle);
                clockCycle++;
                savesignalStrenght();
            } while (!currentInstruction.isExecutionCompleted());

            register = currentInstruction.getResult(register);
        }

    }

    private void savesignalStrenght() {
        signalStrenght.computeIfPresent(clockCycle, ( a,b) -> this.clockCycle * this.register );
    }

    public int getSumOfSignalStrenght() {
        return signalStrenght.values().stream().mapToInt(i -> i).sum();
    }



}
