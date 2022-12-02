package it.ruggero.adventofcode2022.day1.part1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static it.ruggero.adventofcode2022.day1.CaloriesCounting.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CaloriesCountingTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day1\\testDay1.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day1\\Day1.txt";

    @Test
    void shouldCountCaloriesWithTestFile() {
        initialize(FILE_PATH_TEST);
        var highestAmountOfCalories = getCaloriesCount().values().stream().max(Comparator.naturalOrder()).orElse(-1);
        assertThat(highestAmountOfCalories).isEqualTo(24000);
    }

    @Test
    void shouldGetTheAmountOfCaloriesFromTheElfCarryingTheMostTest() {
        int amount = getTheAmountOfCaloriesFromTheElfCarryingTheMost(FILE_PATH_TEST);
        assertEquals(amount,24000);
    }


//    @Test
//    void shouldCountCaloriesActualFile() {
//        readFile(FILE_PATH);
//        createMapElfCalories(getLines());
//        countCalories();
//
//        var highestAmountOfCalories = getCaloriesCount().values().stream().max(Comparator.naturalOrder()).orElse(-1);
//        assertThat(highestAmountOfCalories).isEqualTo(24000);
//    }


//    @Test
//    void partTwo() {
//        readFile(FILE_PATH_TEST);
//        createMapElfCalories(getLines());
//        countCalories();
//
//        var highestAmountOfCalories = getCaloriesCount().values().stream().max(Comparator.naturalOrder()).orElse(-1);
//
//        int a =  findTheTopThreeElves();
//        assertThat(a).isEqualTo(45000);
//    }
//
//    @Test
//    void partTwoActualFile() {
//        readFile(FILE_PATH);
//        createMapElfCalories(getLines());
//        countCalories();
//        int a =  findTheTopThreeElves();
//        assertThat(a).isEqualTo(45000);
//    }
}
