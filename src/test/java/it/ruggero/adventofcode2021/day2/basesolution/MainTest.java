package it.ruggero.adventofcode2021.day2.basesolution;

import it.ruggero.adventofcode2021.day2.basesolution.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MainTest {

    @BeforeAll
    public static void testReadFile ( ) {
        Main.readFile();

    }

    @Test
    public void testProcessFile( ) {
        Assertions.assertEquals(2036120, Main.processFile());
    }

    @Test
    public void testProcessFileWithAim( ) {
        //System.out.println(" result is " + it.ruggero.adventofcode2021.day1.basesolution.Main.processFileWithAim());
        Assertions.assertEquals(2015547716, Main.processFileWithAim());
    }
}
