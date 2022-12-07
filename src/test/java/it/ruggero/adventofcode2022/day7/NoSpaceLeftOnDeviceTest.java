package it.ruggero.adventofcode2022.day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoSpaceLeftOnDeviceTest {

    private static int PART_1_EXPECTED_RESULT = 2061777;
    private static int PART_2_EXPECTED_RESULT = 4473403;

    @Test
    void shouldSolvePartOne() {
        var partOneResult = NoSpaceLeftOnDevice.partOne();
        Assertions.assertEquals(PART_1_EXPECTED_RESULT,partOneResult);
    }

    @Test
    void shouldSolvePartTwo() {
        var partTwoResult = NoSpaceLeftOnDevice.partTwo();
        Assertions.assertEquals(PART_2_EXPECTED_RESULT,partTwoResult);

    }
}
