package it.ruggero.adventofcode2021.day9;

import it.ruggero.adventofcode2021.day9.standardsolution.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class Day9Test {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day9\\testDay9.txt";
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day9\\day9.txt";

    @Test
    public void testReadFile( ){
        Grid gridTest = new Grid(FILE_PATH_TEST);
        Grid grid = new Grid(FILE_PATH_ACTUAL);
        gridTest.printLowPoints();
        grid.printLowPoints();
        /*gridTest.printSumOfRiskLevels();
        grid.printSumOfRiskLevels();*/
        Assertions.assertEquals(15,gridTest.printSumOfRiskLevels());
        Assertions.assertEquals(448,grid.printSumOfRiskLevels());

    }



    @Test
    public void testBasinCreation() {
        Grid gridTest = new Grid(FILE_PATH_TEST);
        gridTest.printLowPoints();
        gridTest.createSinglePointBasins();
        gridTest.printBasinsText();
        gridTest.defineBasinsFromSinglePoint();
        gridTest.printBasinsText();
        gridTest.getBasins().stream().forEach( b -> System.out.println( b.getBasinPoints().size()));
        Collections.sort(gridTest.getBasins(), (b1,b2) -> b2.getBasinPoints().size() - b1.getBasinPoints().size() );
        gridTest.getBasins().stream().forEach( b -> System.out.println( b.getBasinPoints().size()));
        gridTest.getBasins();
        gridTest.multiplySizeThreeLargestBasins();

        /*Grid grid = new Grid(FILE_PATH_ACTUAL);
        //grid.printLowPoints();
        grid.createSinglePointBasins();
        grid.defineBasinsFromSinglePoint();
        Collections.sort(grid.getBasins(), (b1,b2) -> b2.getBasinPoints().size() - b1.getBasinPoints().size() );
        grid.getBasins().get(0).getBasinPoints().stream().forEach(System.out::println);
        //grid.printBasinsText();


        grid.multiplySizeThreeLargestBasins();
        grid.getBasins().stream().forEach( b -> System.out.println( b.getBasinPoints().size()));*/


    }

    @Test
    public void testprintGrid() {
        Grid gridTest = new Grid(FILE_PATH_TEST);
        gridTest.printGrid();

    }


    @Test
    public void testPrintBasins() {
        //Grid gridTest = new Grid(FILE_PATH_TEST);
        Grid gridTest = new Grid(FILE_PATH_ACTUAL);
        gridTest.printLowPoints();
        gridTest.createSinglePointBasins();
        gridTest.defineBasinsFromSinglePoint();
        gridTest.printBasinsText();
        gridTest.printGrid();
        for(Grid.Basin basin : gridTest.getBasins()) {

            gridTest.printBasin(basin);
        }

    }
}
