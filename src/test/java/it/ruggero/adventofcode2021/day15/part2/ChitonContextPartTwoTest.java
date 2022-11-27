package it.ruggero.adventofcode2021.day15.part2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.part1.ChitonContext.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ChitonContextPartTwoTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";


    @Test
    void shouldCreateCavernMapPartTwo() {
        buildFromFilePartTwo(FILE_PATH_TEST);
        var cavernMap = getCavernMap();
        assertThat(cavernMap).hasDimensions(50,50);
    }

    @Test
    void shouldCalculateMinimumRiskPathWithTestFile() {
        Assertions.assertEquals(315, runPartTwo(FILE_PATH_TEST));
    }

    @Test
    void shouldCalculateMinimumRiskPathWithActualFile() {
        Assertions.assertEquals(3040, runPartTwo(FILE_PATH));
    }

}
