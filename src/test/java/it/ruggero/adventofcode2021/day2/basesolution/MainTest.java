package it.ruggero.adventofcode2021.day2.basesolution;

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
        Assertions.assertEquals(2015547716, Main.processFileWithAim());
    }
}
