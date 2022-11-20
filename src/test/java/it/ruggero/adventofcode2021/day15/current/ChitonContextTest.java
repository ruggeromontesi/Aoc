package it.ruggero.adventofcode2021.day15.current;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.current.ChitonContext.*;

;

public class ChitonContextTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";

    @BeforeAll
    static void build() {
        buildFromFile(FILE_PATH_TEST);
    }

    @Test
    void shouldDo() {
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(2, 3);
        var a = getCavernMap();
        Assertions.assertTrue(a.length > 2);
    }

    @Test
    void shouldMainRunReturnSomething() {
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(2, 3);
        var a = getCavernMap();
        Assertions.assertEquals(Integer.MAX_VALUE, mainRun());
    }
}
