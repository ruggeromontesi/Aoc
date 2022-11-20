package it.ruggero.adventofcode2021.day15.current;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.current.ChitonContext.*;

;

public class ChitonContextTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";



    @Test
    void shouldDo() {
        buildFromFile(FILE_PATH_TEST);
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(2, 3);
        var a = getCavernMap();
        Assertions.assertTrue(a.length > 2);
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
}
