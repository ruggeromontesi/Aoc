package it.ruggero.adventofcode2021.day15.readfile;

import it.ruggero.adventofcode2021.day15.Chiton;
import org.junit.jupiter.api.Test;

public class ParseFileTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";

    @Test
    public void shouldAcquireTestFile(){

        Chiton chiton = new Chiton(FILE_PATH_TEST);
        System.out.println(chiton.getMap());
        chiton.print();

    }
}
