package it.ruggero.adventofcode2021.day15.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";
    @BeforeAll
    static void build() {
        buildFromFile(FILE_PATH_TEST);
    }

    @Test
    void shouldReturnDirectionsWithLowestRisk() {
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(0,0);
        assertEquals(2, findDirectionsWithLowestRisk(c).size() );
    }

    @Test
    void shouldReturnMApCleanedOfSpuriousValues() {
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(4,7);
        assertEquals(2, findDirectionsWithLowestRisk(c).size() );
    }


    @Test
    void shouldReturnDirectionWithLowestRisk() {
        ChitonContext.Coordinate c = new ChitonContext.Coordinate(0,0);
        assertEquals(Direction.SOUTH, findDirectionWithLowestRisk(c) );
        c = new ChitonContext.Coordinate(4,7);
        assertEquals(Direction.EAST, findDirectionWithLowestRisk(c) );
    }

   @Test
    void shouldGo() {
        var a = go();
        Assertions.assertTrue(a > 0);
    }
}
