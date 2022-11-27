package it.ruggero.adventofcode2021.day15.part1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.part1.ChitonContext.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ChitonContextPartOneTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH_TREESET = ".\\src\\main\\resources\\day15\\testDay15TreeSet.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";

    @Test
    void shouldCreateCavernMapPartOne() {
        buildFromFilePartOne(FILE_PATH_TEST);
        var cavernMap = getCavernMap();
        assertThat(cavernMap).hasDimensions(10,10);
    }

    @Test
    void shouldCalculateMinimumRiskPathWithTestFile() {
        Assertions.assertEquals(40, runPartOne(FILE_PATH_TEST));
    }



    @Test
    void issueWithTreeSet() {
        Assertions.assertEquals(739, runPartOne(FILE_PATH_TREESET));
    }

}
