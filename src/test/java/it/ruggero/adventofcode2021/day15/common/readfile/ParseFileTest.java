package it.ruggero.adventofcode2021.day15.common.readfile;

import it.ruggero.adventofcode2021.day15.common.validate.ValidateInput;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.common.readfile.ParseFileUtility.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ParseFileTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";

    @Test
    void shouldAcquireTestFile(){
        readFile(FILE_PATH_TEST);
        var valuesAsStringArray = getValuesAsStringArray();
        assertThat(valuesAsStringArray).hasDimensions(10,10);
    }

    @Test
    void shouldAcquireMainFile(){
        readFile(FILE_PATH);
        var valuesAsStringArray = getValuesAsStringArray();
        assertThat(valuesAsStringArray).hasDimensions(100,100);
    }

    @Test
    void shouldValidateTestFile(){
        readFile(FILE_PATH_TEST);
        ValidateInput.validate(getValuesAsStringArray());
    }

    @Test
    void shouldValidateMainFile(){
        readFile(FILE_PATH);
        ValidateInput.validate(getValuesAsStringArray());
    }

}
