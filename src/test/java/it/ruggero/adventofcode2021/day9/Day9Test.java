package it.ruggero.adventofcode2021.day9;

import it.ruggero.adventofcode2021.day8.Day8;
import org.junit.jupiter.api.Test;

public class Day9Test {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day9\\testDay9.txt";
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day9\\day9.txt";

    @Test
    public void testReadFile( ){
        Grid gridTest = new Grid(FILE_PATH_TEST);
        Grid grid = new Grid(FILE_PATH_ACTUAL);
        gridTest.printLowPoints();
        grid.printLowPoints();
        gridTest.printSumOfRiskLevels();
        grid.printSumOfRiskLevels();

    }

}
