package it.ruggero.adventofcode2021.day9.standardsolution;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Grid {

    private int gridMaxX = 0;

    private int gridMaxY = 0;

    private List<Point> points = new ArrayList<>();

    private List<Point> lowPoints = new ArrayList<>();

    private List<Basin> basins = new ArrayList<>();

    public int getGridMaxX() {
        return gridMaxX;
    }

    public void setGridMaxX(int gridMaxX) {
        this.gridMaxX = gridMaxX;
    }

    public int getGridMaxY() {
        return gridMaxY;
    }

    public void setGridMaxY(int gridMaxY) {
        this.gridMaxY = gridMaxY;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Basin> getBasins() {
        return basins;
    }

    public Grid(String filePath) {
        String line;
        int counter = 0;

        int gridMaxX = 0;

        int lineCount = 0;




        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();

                if (gridMaxX == 0) {
                    this.gridMaxX = line.length();

                } else {
                    if (gridMaxX != line.length()) {
                        throw new RuntimeException("Inconsistent map!");
                    }
                }

                for (int i = 0; i < line .length(); i++) {
                    int height = 0;
                    try {
                        height = Integer.parseInt(line.substring(i, i+1));
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong map!");
                    }

                    points.add(new Point(i,lineCount,height));



                }

                lineCount++;


            }

            this.gridMaxY = lineCount;


        } catch(FileNotFoundException ex) {
            System.out.println( ex );
        }

        findLowPoints();
    }


    public void findLowPoints() {
        points.stream().forEach(  p-> isPointLow(p));
        lowPoints =  points.stream().filter( p -> p.isLow()).collect(Collectors.toList());

    }

    private  void isPointLow(Point point){

         boolean isLow = findNeighbours(point).stream().allMatch( p -> p.getZ() > point.getZ() );

         point.setLow(isLow);
    }

    public List<Point> findNeighbours(Point point) {

        int x = point.getX();
        int y = point.getY();

        List<Point> neighbourN = points.stream().filter( p -> p.getX() == x && p.getY() == y+1).collect(Collectors.toList());
        List<Point> neighbourE = points.stream().filter( p -> p.getX() == x+1 && p.getY() == y).collect(Collectors.toList());
        List<Point> neighbourS = points.stream().filter( p -> p.getX() == x && p.getY() == y-1).collect(Collectors.toList());
        List<Point> neighbourW = points.stream().filter( p -> p.getX() == x-1 && p.getY() == y).collect(Collectors.toList());

        List<Point> neighbours = new ArrayList<>();

        neighbours.addAll(neighbourN);
        neighbours.addAll(neighbourE);
        neighbours.addAll(neighbourS);
        neighbours.addAll(neighbourW) ;

        return neighbours.stream().distinct().collect(Collectors.toList());
    }

    public List<Point> findNeighboursInBasin(Point point) {
        List<Point> candidates = findNeighbours(point);
        List<Point> output = new ArrayList<>();

        output = candidates.stream().filter( p -> p.getZ() < 9 && p.getZ() == point.getZ()+1).collect(Collectors.toList());

        return output;
    }


    public  void printLowPoints() {
        lowPoints.stream().forEach(System.out::println);
        //System.out.println("Low points : " + "/n" + lowPoints);
    }


    public int printSumOfRiskLevels(){
        int sum = lowPoints.stream().mapToInt(Point::getRiskLevel).sum();
        System.out.println("Sum of risk level----" + sum);
        return  sum;
    }

    public void createSinglePointBasins( ) {

        if (lowPoints.size() == 0) {
            return;
        } else {
            basins =  new ArrayList<>();
            for(Point lowPoint : lowPoints) {
                Basin basin = new Basin(lowPoint);
                basin.getBasinPoints().add(lowPoint);

                basins.add( basin );

            }
        }
    }

    public void defineBasinsFromSinglePoint() {
        if( basins.size() != 0) {
            for(Basin basin : basins) {
                List<Point> startingBasinPoints = new ArrayList<>();
                startingBasinPoints.add(basin.lowPointstarting);
                basin.proceed(startingBasinPoints);
                startingBasinPoints = startingBasinPoints.stream().distinct().collect(Collectors.toList());
            }

            for(Basin basin : basins) {
                basin.basinPoints = basin.getBasinPoints().stream().distinct().collect(Collectors.toList());
            }


        }
    }


    public void printBasinsText( ) {

        basins.stream().forEach(System.out::println);
    }


    public int multiplySizeThreeLargestBasins() {
        int output =0;

        Collections.sort(basins, (b1,b2) -> b1.getBasinPoints().size() - b2.getBasinPoints().size() );
        output = basins.stream().limit(3).mapToInt(b -> b.getBasinPoints().size()).reduce(1 , (a,b)-> a*b) ;
        //Collections.sort(basins, Comparator.comparingInt());
        System.out.println("multiplySizeThreeLargestBasins " + output);
        return output;
    }

    public void printGrid() {
        for(int j = 0;  j < gridMaxY; j++ ) {
            System.out.println("");
            for(int i = 0; i < gridMaxX; i++) {
                int x = i;
                int y = j;
                points.stream().filter( p -> p.getX() == x && p.getY() == y).forEach(p -> System.out.print(p.getZ()));

            }
        }
    }


    public void printBasin(Basin basin) {
        for(int j = 0;  j < gridMaxY; j++ ) {
            System.out.println("");
            for(int i = 0; i < gridMaxX; i++) {
                int x = i;
                int y = j;
                List<Point> matchingPoints = basin.getBasinPoints().stream().filter( p -> p.getX() == x && p.getY() == y).collect(Collectors.toList());
                if ( matchingPoints.size() == 1) {
                    System.out.print(matchingPoints.get(0).getZ());
                } else {
                    System.out.print("*");
                }
                //points.stream().filter( p -> p.getX() == x && p.getY() == y).forEach(p -> System.out.print(p.getHeight()));

            }
        }

    }




    public class Basin {

        private Point lowPointstarting;

        private List<Point> basinPoints =  new ArrayList<>();

        public Basin(Point lowPointstarting) {
            this.lowPointstarting = lowPointstarting;
        }

        public Point getLowPointstarting() {
            return lowPointstarting;
        }

        public List<Point> getBasinPoints() {
            return basinPoints;
        }

        public List<Point> proceed(List<Point> points) {

            List<Point> output = new ArrayList<>();

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
                    "lowPointstarting=" + lowPointstarting +
                    ", basinPoints=" + basinPoints +
                    '}';
        }


    }

}

/*

RUBBISH

        List<Point> output = new ArrayList<>();

        List<Point> neighbours = findNeighbours(point);

        output = neighbours.stream().filter( p->
                p.getHeight() != 9 &&
                        p.getHeight() == point.getHeight()+1
                ).collect(Collectors.toList());

        while (neighbours.size() > 0) {

        }

        return output.stream().distinct().collect(Collectors.toList());
    }


    private List<Point> findBasin(Point point) {
        List<Point> output = new ArrayList<>();
        List<Point> initialNeighbours = findNeighbours(point);

        while(initialNeighbours.size() > 0) {
            for(Point p : initialNeighbours) {
                //findBasin()
            }
        }


        return output.stream().distinct().collect(Collectors.toList());
    }


    public List<Point> findBasins() {

        List<Point> output = new ArrayList<>();

        for(Point point : lowPoints) {

        }
        return output;
    }

 */
