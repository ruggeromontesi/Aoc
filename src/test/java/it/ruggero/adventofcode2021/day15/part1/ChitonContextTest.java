package it.ruggero.adventofcode2021.day15.part1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.part1.ChitonContext.*;



public class ChitonContextTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";



    @Test
    void shouldDo() {
        buildFromFile(FILE_PATH_TEST);
        var a = getCavernMap();
        Assertions.assertTrue(a.length > 2);
    }

    @Test
    void newBuildFromFileTest() {
        buildFromFileNewSimple(FILE_PATH_TEST);
        Assertions.assertEquals(40, mainRun());
    }

    @Test
    void shouldMainRunCalculateOverTestFile() {
        buildFromFile(FILE_PATH_TEST);
        Assertions.assertEquals(40, mainRun());
    }

    @Test
    void shouldMainRunCalculateOverMainFile() {
        buildFromFile(FILE_PATH);
        Assertions.assertEquals(739, mainRun());
    }


    @Test
    void shouldMainRunExtendedOverTestFile() {
        Assertions.assertEquals(315,mainRunExtended(FILE_PATH_TEST));
    }

    @Test
    void shouldMainRunExtendedOverMainFile() {
        Assertions.assertEquals(3040,mainRunExtended(FILE_PATH));
    }
}
