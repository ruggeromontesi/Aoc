package it.ruggero.adventofcode2022.day1.common;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static it.ruggero.adventofcode2022.day1.CaloriesCounting.*;
import static it.ruggero.adventofcode2022.day1.common.ParseFileUtility.getLines;
import static it.ruggero.adventofcode2022.day1.common.ParseFileUtility.readFile;
import static org.assertj.core.api.Assertions.assertThat;

public class ParseFileUtilityTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day1\\testDay1.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day1\\Day1.txt";

    @Test
    void shouldAcquireTestFile(){
        readFile(FILE_PATH_TEST);
        var lines  = getLines();
        assertThat(lines).hasSize(14);
    }
   @Test
    void shouldGenerateCaloriesMap() {
       readFile(FILE_PATH_TEST);
       createMapElfCalories(getLines());
       var elfCalories = getCalories();
       assertThat(elfCalories).hasSize(5);
    }






}
