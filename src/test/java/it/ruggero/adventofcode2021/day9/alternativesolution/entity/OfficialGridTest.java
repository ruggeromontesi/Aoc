
package it.ruggero.adventofcode2021.day9.alternativesolution.entity;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class OfficialGridTest {

    private static final String FILE_PATH_TEST_ZERO = ".\\src\\main\\resources\\day9\\simpleTest.txt";
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day9\\testDay9.txt";
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day9\\day9.txt";

    @Test
    public void testAssignBasinToThisPoint() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        Point point = testGrid.getMap()[0][0];
        //testGrid.discoverBasinStartingFromThisPoint(point);
        System.out.println("Assigned basin ----" + point.getBasin());
        System.out.println("Basin list  = " );
        testGrid.getBasins().forEach(System.out::println);

    }

    @Test
    public void testPointsBelongToTheSameBasin() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        Point point1 = testGrid.getMap()[3][1];
        Point point2 = testGrid.getMap()[3][2];

        boolean sameBasin = testGrid.areNeighbouringPointsInTheSameBasin(point1,point2);
        System.out.println("sameBasin   " + sameBasin);
    }

    @Test
    public void testProceed() {
        String FILE_PATH = ".\\src\\main\\resources\\day9\\day9Test1.txt";
        Grid testGrid = new Grid(FILE_PATH_ACTUAL);
        Set pointsToTest = new HashSet<>();
        Point point = testGrid.getMap()[9][4];
        Grid.Basin basin = testGrid.new Basin();
        point.setBasin(basin);
        testGrid.getBasins().add(basin);
        basin.getBasinPoints().add(point);
        pointsToTest.add(point);
        testGrid.proceed(pointsToTest);
        testGrid.getBasins().forEach( b -> {
            System.out.println( "Id of basin " + b.getId());
            System.out.println("basin size " + b.getBasinPoints().size());
            b.getBasinPoints().forEach( point1 -> {
                System.out.println( "[" + point1.getY() + "][" + point1.getX() +"]--Height[" + point1.getHeight() + "]");
            });
        });

    }


    @Test
    public  void testAssignBasin() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        testGrid.assignBasin();

        testGrid.getBasins().forEach( b -> {
            System.out.println( "Id of basin " + b.getId());
            System.out.println("basin size " + b.getBasinPoints().size());
            b.getBasinPoints().forEach( point1 -> {
                System.out.println( "[" + point1.getY() + "][" + point1.getX() +"]--Height[" + point1.getHeight() + "]");
            });
        });
    }



    @Test
    public  void testMultiply() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        testGrid.assignBasin();
        testGrid.multiplySizeOfThreeBiggestBasins();


    }
}
