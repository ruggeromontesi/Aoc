package it.ruggero.adventofcode2021.day9.alternativesolution.entity;


public class Point {



    private int height;

    private int riskLevel;

    private boolean isLow = false;

    public Point(int z) {
        this.height = z;
        setRiskLevel();
    }



    public int getHeight() {
        return height;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    private void setRiskLevel() {
        this.riskLevel = this.height + 1;
    }

    public boolean isLow() {
        return isLow;
    }

    public void setLow(boolean low) {
        isLow = low;
    }





}
