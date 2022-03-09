package it.ruggero.adventofcode2021.day9.alternativesolution.entity;


public class Point {

    private final int x;

    private final int y;

    private final int height;

    private int riskLevel;

    private boolean isLow = false;

    /*public Point(int z) {
        this.height = z;
        setRiskLevel();
    }*/

    public Point(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        setRiskLevel();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", height=" + height +
                ", riskLevel=" + riskLevel +
                ", isLow=" + isLow +
                '}';
    }
}
