package it.ruggero.adventofcode2021.day9.alternativesolution.entity;


public class Point implements Comparable<Point> {

    private final int x;

    private final int y;

    private final int height;

    private int riskLevel;

    private boolean isLow = false;

    private boolean basinChecked;

    private Grid.Basin basin;

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

    public Grid.Basin getBasin() {
        return basin;
    }

    public void setBasin(Grid.Basin basin) {
        if (this.basin == null) {
            this.basin = basin;
        } else {
            if (!this.basin.equals(basin)) {
                throw  new RuntimeException("The same point was assigned two times with different basins :  old basin " +
                        this.basin + "   new basin" + basin);
            }
        }

    }

    public boolean isBasinChecked() {
        return basinChecked;
    }

    public void setBasinChecked(boolean basinChecked) {
        this.basinChecked = basinChecked;
    }

    public int compareTo(Point point) {
        int output = 0;
        output = (y - point.getY() != 0) ? (y - point.getY()) : (x-point.getX());
        //output = 100*(x-point.getX())+(y - point.getY());
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (getX() != point.getX()) return false;
        if (getY() != point.getY()) return false;
        return getHeight() == point.getHeight();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        result = 31 * result + getHeight();
        return result;
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
