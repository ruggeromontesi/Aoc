package it.ruggero.adventofcode2021.day9.alternativesolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Grid {

    private int gridMaxX = 0;

    private int gridMaxY = 0;

    private int sumOfRiskLevelsOFAllLowPoints = 0;

    private Point[][] map ;

    private List<Basin> basins = new ArrayList<>();

    public int getSumOfRiskLevelsOFAllLowPoints() {
        return sumOfRiskLevelsOFAllLowPoints;
    }

    public Point[][] getMap() {
        return map;
    }

    public List<Basin> getBasins() {
        return basins;
    }

    public Grid(String filePath) {

        String line;

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();
                gridMaxY++;
                if (gridMaxX == 0) {
                    this.gridMaxX = line.length();

                } else {
                    if (gridMaxX != line.length()) {
                        throw new RuntimeException("Inconsistent map!");
                    }

                }
            }
        } catch (FileNotFoundException
                ex) {
            System.out.println(ex);
        }

        map = new Point[gridMaxY][gridMaxX];

        try (Scanner input = new Scanner(new File(filePath))) {
            int j = 0;
            while (input.hasNextLine()) {
                line = input.nextLine();
                for(int i = 0; i < this.gridMaxX; i++) {
                    int height = 0;
                    try {
                        height = Integer.parseInt(line.substring(i, i+1));
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong map!");
                    }
                    //map[j][i] = new Point(height);
                    map[j][i] = new Point(i,j,height);
                }

                j++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        checkIfPointsAreLow();
        calculateSumOfRiskLevelsOFAllLowPoints();

    }


    public void printGrid() {
        for(int j = 0; j < map.length; j++) {
            System.out.println("");
            for(int i = 0; i < map[j].length; i++) {
                System.out.print(map[j][i].getHeight() + " ");
            }
        }
    }

    public void printLowPoints() {
        for(int j = 0; j < map.length; j++) {
            System.out.println("");
            for(int i = 0; i < map[j].length; i++) {
                if(map[j][i].isLow() ) {
                    System.out.print(map[j][i].getHeight() + " ");
                } else {
                    System.out.print("* ");
                }

            }
        }
    }




    public void isPointLow(int x, int y ) {
        Point thisPoint  = map[y][x];

        //NORTH BORDER
        if (y == 0) {
            //NORTH BORDER NOT CORNER
            if (x > 0 && x < gridMaxX -1) {
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                basins.add(new Basin(thisPoint));

                return;
            }
            //NORTH WEST CORNER
            if(x == 0) {
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                basins.add(new Basin(thisPoint));
                return;
            }
            //NORTH EAST CORNER
            if(x == gridMaxX-1) {
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                basins.add(new Basin(thisPoint));
                return;
            }


        }

        //SOUTH BORDER
        if (y == gridMaxY -1 ) {
            //SOUTH BORDER NOT CORNER
            if (x > 0 && x < gridMaxX -1) {
                //CHECK NORTH NEIGHBOUR
                if (!isLowerThanNorthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }

                thisPoint.setLow(true);
                basins.add(new Basin(thisPoint));
                return;
            }
            //SOUTH WEST CORNER
            if(x == 0) {
                //CHECK NORTH NEIGHBOUR
                if (!isLowerThanNorthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                basins.add(new Basin(thisPoint));
                return;
            }
            //SOUTH EAST CORNER
            if(x == gridMaxX-1) {
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                basins.add(new Basin(thisPoint));
                return;
            }


        }

        //ON WEST BORDER NOT IN CORNERS
        if (x == 0 && y < gridMaxY - 1 ) {
            //CHECK NORTH NEIGHBOUR
            if (!isLowerThanNorthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK EAST NEIGHBOUR
            if (!isLowerThanEastNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK SOUTH NEIGHBOUR
            if(!isLowerThanSouthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            thisPoint.setLow(true);
            basins.add(new Basin(thisPoint));
            return;
        }


        //ON EAST BORDER NOT IN CORNERS
        if (x == gridMaxX -1  && y < gridMaxY - 1 ) {
            //CHECK NORTH NEIGHBOUR
            if (!isLowerThanNorthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK WEST NEIGHBOUR
            if (!isLowerThanWestNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK SOUTH NEIGHBOUR
            if(!isLowerThanSouthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            thisPoint.setLow(true);
            basins.add(new Basin(thisPoint));
            return;

        }

        //ALL OTHER CASES
        //CHECK NORTH NEIGHBOUR
        if( x > 0 && x < gridMaxX -1 && y > 0 && y < gridMaxY - 1) {
            if (!isLowerThanNorthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK EAST NEIGHBOUR
            if (!isLowerThanEastNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK SOUTH NEIGHBOUR
            if(!isLowerThanSouthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK WEST NEIGHBOUR
            if (!isLowerThanWestNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }

            thisPoint.setLow(true);
            basins.add(new Basin(thisPoint));
            return;
        }
    }



    private void checkIfPointsAreLow() {
        for( int j = 0; j < map.length; j++){
            for(int i = 0; i < map[j].length; i++) {
                isPointLow(i,j);
            }
        }
    }


    private boolean isLowerThanNorthNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y-1][x].getHeight()) {
            return false;
        } else {
            return  true;
        }
    }

    private boolean isLowerThanEastNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y][x+1].getHeight()) {
            return false;
        } else {
            return  true;
        }

    }

    private boolean isLowerThanSouthNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y+1][x].getHeight()) {
            return false;
        } else {
            return  true;
        }

    }

    private boolean isLowerThanWestNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y][x-1].getHeight()) {
            return false;
        } else {
            return  true;
        }

    }


    public void calculateSumOfRiskLevelsOFAllLowPoints( ) {
        int result = 0;
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if ( map[j][i].isLow()) {
                    result += map[j][i].getRiskLevel();
                }
            }
        }

        sumOfRiskLevelsOFAllLowPoints = result;


    }

    public Set<Point> findNeighboursInBasin(Point point) {
        Set<Point> neighboursInBasin = new HashSet<>();
        //CHECK NORTH NEIGHBOUR
        if (
                (point.getY() > 0) &&
                (map[point.getY()-1][point.getX()].getHeight() < 9) &&
                (point.getHeight()+1 == map[point.getY()-1][point.getX()].getHeight()) )
        {
            neighboursInBasin.add(map[point.getY()-1][point.getX()]);
        }
        //CHECK EAST NEIGHBOUR
        if (
                (point.getX() < gridMaxX -1) &&
                        //
                (map[point.getY()][point.getX()+1].getHeight() < 9) &&
                (point.getHeight()+1 == map[point.getY()][point.getX()+1].getHeight())
        )
        {
            neighboursInBasin.add(map[point.getY()][point.getX()+1]);
        }
        //CHECK SOUTH NEIGHBOUR
        if (
                (point.getY()< gridMaxY - 1) &&
                (map[point.getY()+1][point.getX()].getHeight() < 9) &&
                (point.getHeight()+1 == map[point.getY()+1][point.getX()].getHeight())
        )
        {
            neighboursInBasin.add(map[point.getY()+1][point.getX()]);
        }
        //CHECK WEST NEIGHBOUR
        if (
                (point.getX() > 0) &&
                (map[point.getY()][point.getX()-1].getHeight() < 9) &&
                (point.getHeight() + 1 == map[point.getY()][point.getX()-1].getHeight())
        )
        {
            neighboursInBasin.add(map[point.getY()][point.getX()-1]);
        }


        return neighboursInBasin;
    }


    public void calculateAllBasins() {
        if( basins.size() != 0) {
            for(Basin basin : basins) {

                if( basin.basinPoints.size() == 1) {
                    basin.proceed(basin.basinPoints);
                }
            }

        }
    }

    public int multiplySizesOfThreeLargestBasins() {
        int output = 1;
        Collections.sort(basins, (b1,b2) -> b2.getBasinPoints().size() - b1.getBasinPoints().size());
        basins.stream().forEach( b -> {
            System.out.println("size----" + b.getBasinPoints().size());
        });

        output = basins.stream().limit(3).mapToInt(b -> b.getBasinPoints().size()).reduce(1, (s1, s2) -> s1*s2);
        System.out.println("multiplySizesOfThreeLargestBasins  " + output);
        return  output;
    }

    public class Basin {
        private Point lowPoint;

        private Set<Point> basinPoints = new HashSet<>();

        public Point getLowPoint() {
            return lowPoint;
        }

        public Set<Point> getBasinPoints() {
            return basinPoints;
        }

        public Basin(Point lowPoint) {
            this.lowPoint = lowPoint;
            basinPoints.add(lowPoint);
        }


        public Set<Point> proceed(Set<Point> points) {

            Set<Point> output = new HashSet<>();

            if( points.isEmpty()) {
                return  output;
            } else {
                for(Point point : points) {
                    output = findNeighboursInBasin(point);
                    basinPoints.addAll(output);
                    proceed(output);

                }
            }
            return output;
        }

        @Override
        public String toString() {
            return "Basin{" +
                    "lowPoint=" + lowPoint +
                    ", basinPoints=" + basinPoints +
                    '}';
        }
    }


}
