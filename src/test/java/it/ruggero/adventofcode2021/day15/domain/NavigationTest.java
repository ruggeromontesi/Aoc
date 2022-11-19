package it.ruggero.adventofcode2021.day15.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.buildFromFile;
import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.findDirectionsWithLowestRisk;

public class NavigationTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";
    @BeforeAll
    static void build() {
        buildFromFile(FILE_PATH_TEST);
    }

    @Test
    void shouldReturnDirectionWithLowestRisk() {
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(0,0);
        Assertions.assertNull(findDirectionsWithLowestRisk(c));
    }
}
