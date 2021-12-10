package it.ruggero.adventofcode2021.day5;

import org.junit.jupiter.api.Assertions;
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
        DayFive.processOnlyHorizontalAndVertical();
        Assertions.assertEquals(6572, DayFive.getPointsFinal());

    }


    @Test
    public void testProcessAll() {
        String filePath = ".\\src\\main\\resources\\day5\\day5.txt";
        //filePath = ".\\src\\main\\resources\\day5\\day5test.txt";
        DayFive.readFile(filePath);
        DayFive.processAll();
        //DayFive.processOnlyHorizontalAndVertical();
        System.out.println("Final points " + DayFive.getPointsFinal());

        Assertions.assertEquals(21466, DayFive.getPointsFinal());

    }


}
