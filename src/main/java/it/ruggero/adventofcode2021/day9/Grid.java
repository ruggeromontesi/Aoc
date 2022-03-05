package it.ruggero.adventofcode2021.day9;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid {

    private int gridMaxX = 0;

    private int gridMaxY = 0;

    private List<Point> points = new ArrayList<>();

    private List<Point> lowPoints = new ArrayList<>();

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
         int x = point.getX();
         int y = point.getY();

         List<Point> neighbourN = points.stream().filter( p -> p.getX() == x && p.getY() == y+1).collect(Collectors.toList());
         List<Point> neighbourE = points.stream().filter( p -> p.getX() == x+1 && p.getY() == y).collect(Collectors.toList());
         List<Point> neighbourS = points.stream().filter( p -> p.getX() == x && p.getY() == y-1).collect(Collectors.toList());
         List<Point> neighbourW = points.stream().filter( p -> p.getX() == x-1 && p.getY() == y).collect(Collectors.toList());

        boolean isLow  = Stream.of(neighbourN, neighbourE, neighbourS,neighbourW).flatMap(Collection::stream).collect(Collectors.toList()).stream().allMatch( p -> p.getZ() > point.getZ() );

         point.setLow(isLow);
    }

    public  void printLowPoints() {
        System.out.println("Low points : " + "/n" + lowPoints);
    }


    public void printSumOfRiskLevels(){
        int sum = lowPoints.stream().mapToInt(Point::getRiskLevel).sum();
        System.out.println("Sum of risk level----" + sum);
    }

}
