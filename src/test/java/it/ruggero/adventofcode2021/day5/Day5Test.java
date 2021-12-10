package it.ruggero.adventofcode2021.day5;

import org.junit.jupiter.api.Test;

public class Day5Test {

    @Test
    public void testReadFileSimple() {
        String filePathTest = ".\\src\\main\\resources\\day5\\day5test.txt";
        DayFive.readFile(filePathTest);

    }

    @Test
    public void testReadFile() {
        String filePath = ".\\src\\main\\resources\\day5\\day5.txt";
        DayFive.readFile(filePath);

    }

    @Test
    public void testProcess() {
        String filePath = ".\\src\\main\\resources\\day5\\day5.txt";
        //filePath = ".\\src\\main\\resources\\day5\\day5test.txt";
        DayFive.readFile(filePath);
        DayFive.process();

    }


}
