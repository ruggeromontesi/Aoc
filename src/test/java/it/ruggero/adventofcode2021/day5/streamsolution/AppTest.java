package it.ruggero.adventofcode2021.day5.streamsolution;

import it.ruggero.adventofcode2021.day5.streamsolution.entity.HydrotermalVentureMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day5\\HydrotermalVentureMapInputSampleCase.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day5\\HydrotermalVentureMapInput.txt";

    @Test
    public void testAll() {
        HydrotermalVentureMap hydrotermalVentureMap = new HydrotermalVentureMap(FILE_PATH_TEST);
        hydrotermalVentureMap.createMapWitNumberOfOverlappingLines();
        int[][] numberOfOverlappingLines = hydrotermalVentureMap.getMapWithNumberOfOverlappingLines();
        for (int rowIndex = 0; rowIndex < numberOfOverlappingLines.length; rowIndex++) {
            System.out.println("");
            for (int columnIndex = 0; columnIndex < numberOfOverlappingLines[rowIndex].length; columnIndex++) {
                System.out.print(numberOfOverlappingLines[rowIndex][columnIndex]);
            }

        }

        System.out.println("The number of points where at least two lines overlap is " + hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());
    }

    @Test
    public void testBigCase() {
        HydrotermalVentureMap hydrotermalVentureMap = new HydrotermalVentureMap(FILE_PATH_ACTUAL);
        Long start = System.currentTimeMillis();
        hydrotermalVentureMap.createMapWitNumberOfOverlappingLines();
        Long stop = System.currentTimeMillis();
        Assertions.assertEquals(21466,hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());
        System.out.println("The number of points where at least two lines overlap is " + hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());
        System.out.println("Execution time in ms without concurrency "+ (stop - start));


    }


    @Test
    public void testBigCase2() {
        HydrotermalVentureMap hydrotermalVentureMap = new HydrotermalVentureMap(FILE_PATH_ACTUAL);
        hydrotermalVentureMap.createMapWitNumberOfOverlappingLinesE();
        System.out.println("The number of points where at least two lines overlap is " + hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());
        Assertions.assertEquals(21466,hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());

    }
}
