package it.ruggero.adventofcode2021.day15.common.readfile;

import it.ruggero.adventofcode2021.day15.common.validate.ValidateInput;
import org.junit.jupiter.api.Test;

public class ParseFileTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";

    @Test
    void shouldAcquireTestFile(){
        new ParseFileUtility(FILE_PATH_TEST);
    }
    @Test
    void shouldAcquireMainFile(){
        new ParseFileUtility(FILE_PATH);
    }

    @Test
    void shouldValidateTestFile(){
        var lines = new ParseFileUtility(FILE_PATH_TEST);
        ValidateInput.validate(lines.getLines());
    }


    @Test
    void shouldValidateMainFile(){
        var lines = new ParseFileUtility(FILE_PATH);
        ValidateInput.validate(lines.getLines());
    }



}
