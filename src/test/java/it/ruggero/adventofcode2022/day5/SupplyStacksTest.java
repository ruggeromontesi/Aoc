package it.ruggero.adventofcode2022.day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplyStacksTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day5\\testDay5.txt";

    private static final String RESULT_PART_1_EXAMPLE = "CMZ";
    private static final String RESULT_PART_1 = "JRVNHHCSJ";
    private static final String RESULT_PART_2_EXAMPLE = "MCD";
    private static final String RESULT_PART_2 = "GNFBSBJLH";

    @Test
    void shouldRunPartOneSimpleCase() {
        SupplyStacks.runPartOne(FILE_PATH_TEST);
        var result = SupplyStacks.whatCratesEndsUpOnTopOfEachStack();
        assertThat(result).isEqualTo(RESULT_PART_1_EXAMPLE);
    }

    @Test
    void shouldRunPartOne() {
        SupplyStacks.runPartOne();
        var result = SupplyStacks.whatCratesEndsUpOnTopOfEachStack();
        assertThat(result).isEqualTo(RESULT_PART_1);
    }

    @Test
    void shouldRunPartTwoSimpleCase() {
        SupplyStacks.runPartTwo(FILE_PATH_TEST);
        var result = SupplyStacks.whatCratesEndsUpOnTopOfEachStack();
        assertThat(result).isEqualTo(RESULT_PART_2_EXAMPLE);
    }

    @Test
    void shouldRunPartTwo() {
        SupplyStacks.runPartTwo();
        var result = SupplyStacks.whatCratesEndsUpOnTopOfEachStack();
        assertThat(result).isEqualTo(RESULT_PART_2);
    }
}
