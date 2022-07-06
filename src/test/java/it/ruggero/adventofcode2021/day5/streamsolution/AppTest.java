package it.ruggero.adventofcode2021.day5.streamsolution;

import it.ruggero.adventofcode2021.day5.streamsolution.entity.HydrotermalVentureMap;
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
    }
}
