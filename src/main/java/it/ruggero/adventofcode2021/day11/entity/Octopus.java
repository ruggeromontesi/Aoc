package it.ruggero.adventofcode2021.day11.entity;

import java.util.HashMap;
import java.util.Map;

public class Octopus implements Comparable<Octopus>{

    private final int row;

    private final int column;

    private int energyLevel;

    private Map<Integer,Boolean> flashesOccurred = new HashMap<>();

    private int totalOfNumberFlashes = 0;

    private boolean tobeFlashedInThisStep;



    public Octopus( int row, int column, int energyLevel ) {
        this.energyLevel = energyLevel;
        this.row = row;
        this.column = column;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public boolean isTobeFlashedInThisStep() {
        return tobeFlashedInThisStep;
    }

    public void setTobeFlashedInThisStep(boolean tobeFlashedInThisStep) {
        this.tobeFlashedInThisStep = tobeFlashedInThisStep;
    }

    public void increaseEnergyLevelByOne() {
        if (energyLevel == 9) {
            tobeFlashedInThisStep = true;
        }
        energyLevel++;
    }

    public void resetenergyLevel() {
        energyLevel = 0;
    }



    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Map<Integer, Boolean> getFlashesOccurred() {
        return flashesOccurred;
    }

    public int getTotalOfNumberFlashes() {
        return totalOfNumberFlashes;
    }

    public void calculateTotalOfNumberFlashes() {
        flashesOccurred.forEach(
                (i,b) -> {
                    if (b) {
                        totalOfNumberFlashes++;
                    }
                }
        );
    }

    @Override
    public int compareTo(Octopus o) {
        if(this.getRow() != o.getRow()) {
            return this.getRow() - o.getRow();
        } else {
            return  this.getColumn() - o.column;
        }

    }

    @Override
    public String toString() {
        return "Octopus{" +
                "row=" + row +
                ", column=" + column +
                ", energyLevel=" + energyLevel +
                ", flashesOccurred=" + flashesOccurred +
                '}';
    }
}
