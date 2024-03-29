package it.ruggero.adventofcode2022.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TreetopTreeHouseTest {

    @Test
    void shouldSolvePartOneSample() {
        var result = new TreetopTreeHouse().solvePartOneSample();
        assertThat(result).isEqualTo(21L);
    }

    @Test
    void shouldSolvePartOne() {
        var result = new TreetopTreeHouse().solvePartOne();
        assertThat(result).isEqualTo(1835L);
    }

    @Test
    void shouldSolveParTwoSample() {
        var result = new TreetopTreeHouse().solvePartTwoSample();
        assertThat(result).isEqualTo(8L);
    }

    @Test
    void shouldSolveParTwo() {
        var result = new TreetopTreeHouse().solvePartTwo();
        assertThat(result).isEqualTo(263670L);
    }


}
