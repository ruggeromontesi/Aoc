package it.ruggero.adventofcode2021.day9.standardsolution;


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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (getX() != point.getX()) return false;
        if (getY() != point.getY()) return false;
        return getZ() == point.getZ();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        result = 31 * result + getZ();
        return result;
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
