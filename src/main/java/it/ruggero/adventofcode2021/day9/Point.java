package it.ruggero.adventofcode2021.day9;


public class Point {

    private int x ;

    private int y;

    private int z;

    private int riskLevel;

    private boolean isLow = false;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        setRiskLevel();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    private void setRiskLevel() {
        this.riskLevel = this.z + 1;
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
                ", z=" + z +
                ", riskLevel=" + riskLevel +
                ", isLow=" + isLow +
                '}';
    }
}
