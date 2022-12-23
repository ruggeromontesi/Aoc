package it.ruggero.adventofcode2022.day3;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static it.ruggero.adventofcode2022.day3.Rucksack.*;
import static it.ruggero.util.input.old.ParseFileUtility.*;
import static org.assertj.core.api.Assertions.assertThat;

class RucksackTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day3\\testDay3.txt";

    private final static int PART_ONE_RESULT = 8185;
    private final static int PART_TWO_RESULT = 2817;

    private static final String LINE_1 = "vJrwpWtwJgWrhcsFMMfFFhFp";

    @Test
    void shouldFindItemsInBothCompartments() {
        var result = findItemsInBothCompartments(LINE_1);
        assertThat(result).isEqualTo(Set.of('p'));

        readFile(FILE_PATH_TEST);

        var out = getLines().stream().map(Rucksack::findItemsInBothCompartments).collect(Collectors.toList());

        assertThat(out).hasSize(6);


    }

    @Test
    void shouldCalculatePriorityOfTheItemInBothCompartments() {
        var result = priorityOfItemAppearingInBothCompartments(LINE_1);
        assertThat(result).isEqualTo(16);
    }

    @Test
    void shouldCalculateSumOfPriorities() {
        readFile(FILE_PATH_TEST);

        var out = getLines().stream().mapToInt(Rucksack::priorityOfItemAppearingInBothCompartments).sum();

        assertThat(out).isEqualTo(157);

    }

    @Test
    void shouldRunPartOne() {
        var result = partOne();
        assertThat(result).isEqualTo(PART_ONE_RESULT);
    }

    @Test
    void shouldSplitIntoGroups() {
        var result = splitIntoGroups();
        assertThat(result).hasSize(100);
    }

    @Test
    void shouldFindCommonItemsInGroups() {
        var x = splitIntoGroups();
        var y = findCommonItemsInGroup(x.get(0));
        assertThat(findCommonItemsInGroup(x.get(0))).isEqualTo('r');
    }

    @Test
    void shouldPartTwo() {
        var result = partTwo();
        assertThat(result).isEqualTo(PART_TWO_RESULT);
    }
}
