package it.ruggero.adventofcode2022.Day10;

import it.ruggero.adventofcode2022.Day10.operations.GenericOperation;

import java.util.*;

public class CathodeRayTube {
    private final Queue<GenericOperation> instructions;
    private int clockCycle = 0;
    private int register = 1;
    private final Map<Integer,Integer> signalStrenght = new HashMap<>();
    char[][] crt = new char[6][40];

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
                saveSignalStrength();
                writeOnCRT();
            } while (!currentInstruction.isExecutionCompleted());
            register = currentInstruction.getResult(register);
        }
    }

    private void saveSignalStrength() {
        signalStrenght.computeIfPresent(this.clockCycle, (a,b) -> this.clockCycle * this.register );
    }

    public int getSumOfSignalStrength() {
        return signalStrenght.values().stream().mapToInt(i -> i).sum();
    }

    private void writeOnCRT() {
        int p = clockCycle - 1;
        p = p%40;
        if( p > register - 2 && p < register + 2) {
            crt[((clockCycle - 1)/ 40)][(clockCycle - 1)%40] = '#';
        } else {
            crt[((clockCycle - 1)/ 40)][(clockCycle - 1)%40] = '.';
        }
    }

    public void printEnhanced() {
        for (char[] row : crt) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
