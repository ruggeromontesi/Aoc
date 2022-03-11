package it.ruggero.adventofcode2021.day9.alternativesolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.*;

public class Grid {

    static int counter = 0;

    private int gridMaxX = 0;

    private int gridMaxY = 0;

    private int sumOfRiskLevelsOFAllLowPoints = 0;

    private Point[][] map ;

    private List<Basin> basins = new ArrayList<>();
    Basin basinOfHighPoint = new Basin();

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
        //checkIfPointsAreLow();
        //calculateSumOfRiskLevelsOFAllLowPoints();

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

    /*OK*/
    public Point getNeighbour(Point point,Direction direction) {
        int thisPointX = point.getX();
        int thisPointY = point.getY();

        if (
                thisPointX < 0 ||
                        thisPointX > gridMaxX ||
                        thisPointY < 0 ||
                        thisPointY > gridMaxY
        ) {
            throw  new RuntimeException("The point [" + point.getX() + point.getY() +"] is not within the grid");
        }

        if (direction == Direction.NORTH) {
            if ( thisPointY > 0) {
                return map[thisPointY - 1][thisPointX];
            } else {
                return null;
            }
        }

        if (direction == Direction.EAST) {
            if ( thisPointX < gridMaxX - 1) {
                return  map[thisPointY][thisPointX+1];
            } else {
                return null;
            }
        }

        if (direction == Direction.SOUTH) {
            if ( thisPointY < gridMaxY -1) {
                return  map[thisPointY+1][thisPointX];
            } else {
                return null;
            }
        }

        if (direction == Direction.WEST) {
            if (thisPointX > 0) {
                return map[thisPointY][thisPointX-1];
            } else {
                return null;
            }
        }
        return  null;
    }

    public Set<Point> getAllNeighbours(Point thisPoint) {
        Set<Point> neighbours = new HashSet<>();
        for(Direction direction: Direction.values()) {
            Point neighbour = getNeighbour(thisPoint,direction);
            if(neighbour != null) {
                neighbours.add(neighbour);
            }
        }
        return  neighbours;
    }

    /*OK*/
    public void findAllLowPoints(){
        for(int j = 0; j < map.length; j++) {
            for(int i = 0; i < map[j].length; i++) {
                isThisPointLow(map[j][i]);

            }
        }

    }

    /*OK*/
    public boolean isThisPointLow(Point thisPoint) {
        for (Direction direction : Direction.values()) {
            Point neighbour = getNeighbour(thisPoint,direction);
            if (neighbour != null && thisPoint.getHeight() >= neighbour.getHeight()) {
                thisPoint.setLow(false);
                return false;
            }
        }

        thisPoint.setLow(true);
        return true;
    }


    public Set<Point> findNeighboursInBasin(Point thisPoint) {
        Set<Point> neighboursInBasin = new HashSet<>();
        Basin basin = thisPoint.getBasin();
        if(basin == null) {
            throw  new RuntimeException("Point needs to have basin set");
        }

        Set<Point> neighbours = getAllNeighbours(thisPoint);
        for(Point neighbour : neighbours) {
            if( areNeighbouringPointsInTheSameBasin(thisPoint,neighbour)) {
                basin.getBasinPoints().add(neighbour);
                neighbour.setBasin(basin);
                if (!neighbour.isBasinChecked()) {
                    neighboursInBasin.add(neighbour);
                }

            }
        }

        thisPoint.setBasinChecked(true);

        return neighboursInBasin;

    }

    /* OK*/
    public boolean areNeighbouringPointsInTheSameBasin(Point thisPoint, Point otherPoint) {

        if (thisPoint.getHeight() == 9 || otherPoint.getHeight() == 9) {
            return  false;
        }

        Set<Point> neighbouringPoints = getAllNeighbours(thisPoint);
        if (!neighbouringPoints.contains(otherPoint)) {
            return false;
        }

        if (
                thisPoint.getHeight() == otherPoint.getHeight() + 1 ||
                thisPoint.getHeight() == otherPoint.getHeight() - 1 ||
                thisPoint.getHeight() == otherPoint.getHeight()
        ) {
            return  true;
        }

        return false;
    }


    public Set<Point> proceed(Set<Point> points) {
        Set<Point> output = new HashSet<>();

        if( points.isEmpty()) {
            return  output;
        } else {
            for(Point point : points) {
                output = findNeighboursInBasin(point);
                proceed(output);
            }
        }
        return output;
    }

    public void assignBasin() {
        for(int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                Point point = map[j][i];
                if(point.getHeight() == 9) {
                    point.setBasin(basinOfHighPoint);
                    basinOfHighPoint.getBasinPoints().add(point);
                    point.setBasinChecked(true);
                }

                if( !point.isBasinChecked()) {
                    Basin basin = new Basin();
                    basins.add(basin);
                    basin.getBasinPoints().add(point);
                    point.setBasin(basin);
                    Set<Point> points = new TreeSet<>();
                    points.add(point);
                    proceed(points);
                }
            }
        }
    }


    public void multiplySizeOfThreeBiggestBasins(){
        Collections.sort(basins,(b1,b2) -> b2.getBasinPoints().size() - b1.getBasinPoints().size());
        basins.stream().limit(3).forEach( b -> System.out.println(b.getBasinPoints().size()));
        int output = basins.stream().limit(3).mapToInt( b -> b.getBasinPoints().size()).reduce(1, (a,b) ->a*b);
        System.out.println(output);
    }




    public class Basin {


        private int id;

        private Point lowPoint;

        private Set<Point> basinPoints = new TreeSet<>();

        public Point getLowPoint() {
            return lowPoint;
        }

        public Set<Point> getBasinPoints() {
            return basinPoints;
        }

        public int getId() {
            return id;
        }

        public Basin() {
            id = ++counter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Basin)) return false;

            Basin basin = (Basin) o;

            if (getId() != basin.getId()) return false;
            if (getLowPoint() != null ? !getLowPoint().equals(basin.getLowPoint()) : basin.getLowPoint() != null)
                return false;
            return getBasinPoints() != null ? getBasinPoints().equals(basin.getBasinPoints()) : basin.getBasinPoints() == null;
        }

        @Override
        public int hashCode() {
            int result = getId();
            result = 31 * result + (getLowPoint() != null ? getLowPoint().hashCode() : 0);
            result = 31 * result + (getBasinPoints() != null ? getBasinPoints().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            System.out.println("List of points");
            basinPoints.forEach( p -> {
                System.out.println("[" + p.getX()+"][" + p.getY()+"]" );
            });
            return "Basin{" +
                    "id=" + id +


                    '}';
        }
    }


}
