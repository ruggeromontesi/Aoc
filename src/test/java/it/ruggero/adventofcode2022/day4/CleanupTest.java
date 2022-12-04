package it.ruggero.adventofcode2022.day4;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static it.ruggero.adventofcode2022.day4.Cleanup.*;
import static org.assertj.core.api.Assertions.assertThat;

class CleanupTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day4\\testDay4.txt";

    private static final int PART_ONE_SOLUTION = 571;


    @Test
    void shouldConvertStringToPairSectionAssignment() {
        String inputString = "2-4,6-8";
        var convertedValue = convertStringToPairSectionAssignment(inputString);
        assertThat(convertedValue.getStartElfOne()).isEqualTo(2);
        assertThat(convertedValue.getStopElfOne()).isEqualTo(4);
        assertThat(convertedValue.getStartElfTwo()).isEqualTo(6);
        assertThat(convertedValue.getStopElfTwo()).isEqualTo(8);
    }


    @Test
    void shouldFindOverlappingPairsInTestFile() {
        var x = getAssignmentList(FILE_PATH_TEST).stream().filter(getOneRangeFullyContainsTheOther()).collect(Collectors.toList());
        assertThat(x).hasSize(2);
    }


    @Test
    void shouldFindOverlappingPairs() {
        var x = getAssignmentList().stream().filter(getOneRangeFullyContainsTheOther()).collect(Collectors.toList());
        assertThat(x).hasSize(PART_ONE_SOLUTION);
    }
}
