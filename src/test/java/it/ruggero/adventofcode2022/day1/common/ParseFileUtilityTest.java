package it.ruggero.adventofcode2022.day1.common;

import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2022.day1.common.ParseFileUtility.getLines;
import static it.ruggero.adventofcode2022.day1.common.ParseFileUtility.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class ParseFileUtilityTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day1\\testDay1.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day1\\Day1.txt";

    @Test
    void shouldAcquireTestFile(){
        readFile(FILE_PATH_TEST);
        var lines  = getLines();
        assertThat(lines).hasSize(14);
    }

    @Test
    void shouldAcquireActualFile(){
        readFile(FILE_PATH);
        var lines  = getLines();
        assertThat(lines).hasSize(2265);
    }
}
