package it.ruggero.adventofcode2021.day9.alternativesolution.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class GridTest {
    private static final String FILE_PATH_TEST_ZERO = ".\\src\\main\\resources\\day9\\simpleTest.txt";
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day9\\testDay9.txt";
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day9\\day9.txt";

    @Test
    public void createGridTestTestFile() {

        Grid testGrid = new Grid(FILE_PATH_TEST);

        testGrid.printGrid();
        //testGrid.checkIfPointsAreLow();
        //testGrid.isPointLow(1,0);
        testGrid.printLowPoints();
        //testGrid.calculateSumOfRiskLevelsOFAllLowPoints();
        System.out.println("sum of risk levels " + testGrid.getSumOfRiskLevelsOFAllLowPoints());
        //grid.printGrid();

    }

    @Test
    public void createGridTestMainFile() {

        Grid grid =  new Grid(FILE_PATH_ACTUAL);
        grid.printGrid();
        //grid.checkIfPointsAreLow();
        //testGrid.isPointLow(1,0);
        //System.out.println(" Point 1,0 is low : " + testGrid.getMap()[0][1].isLow());
        grid.printLowPoints();
        System.out.println("sum of risk levels " + grid.getSumOfRiskLevelsOFAllLowPoints());
        //grid.printGrid();

    }



    @Test
    public void calculateSumOfRiskLevelsOfAllLowPointsTest() {

        Grid testGrid = new Grid(FILE_PATH_TEST);
        Assertions.assertEquals(15,testGrid.getSumOfRiskLevelsOFAllLowPoints());
        Grid grid =  new Grid(FILE_PATH_ACTUAL);
        Assertions.assertEquals(448,grid.getSumOfRiskLevelsOFAllLowPoints());

    }

    @Test
    public void testBasinCreation() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        testGrid.getBasins().forEach(System.out::println);

    }



    @Test
    public  void  testFindNeighboursInBasin() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        System.out.println("Printout of low points");
        testGrid.getBasins().forEach( b -> System.out.println(b.getLowPoint()));
        System.out.println("**************************************************");
        //METHOD TESTED ON THE FIRST LOW POINT



    }

    @Test
    public void testCalculateBasins() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
        //testGrid.calculateAllBasins();
        testGrid.getBasins().forEach(
                b -> {
                    System.out.println("Print of basin with low point " + b.getLowPoint());
                    b.getBasinPoints().forEach(
                            p -> {
                                System.out.println("[x: " + p.getX() + "]   [y: " + p.getY() + "]");
                            }
                    );
                }
        );
    }

    @Test
    public void testMultiplySizesOfThreeLargestBasinsTestFile() {
        Grid testGrid = new Grid(FILE_PATH_TEST);
       // testGrid.calculateAllBasins();
       // testGrid.multiplySizesOfThreeLargestBasins();
    }

    @Test
    public void testMultiplySizesOfThreeLargestBasinsActualFile() {
        Grid testGrid = new Grid(FILE_PATH_ACTUAL);
        //testGrid.calculateAllBasins();
        //testGrid.multiplySizesOfThreeLargestBasins();
    }


    @Test
    public void testNewFindBasins(){
        //Grid testGrid = new Grid(FILE_PATH_TEST_ZERO);
        Grid testGrid = new Grid(FILE_PATH_TEST);
        //testGrid.calculateAllBasins();
        testGrid.getBasins().forEach(
                b -> {
                    System.out.println("Print of basin with id " + b.getId());
                    b.getBasinPoints().forEach(
                            p -> {
                                System.out.println("[x: " + p.getX() + "]   [y: " + p.getY() + "]");
                            }
                    );
                }
        );
    }


}
