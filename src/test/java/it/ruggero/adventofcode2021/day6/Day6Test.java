/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class Day6Test {
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day6\\fishes.txt";
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day6\\testFishes.txt";
    private static  String filePath = FILE_PATH_ACTUAL;


    @Test
    public void testReadFile() {
        String filePath = FILE_PATH_TEST;
        int days = 80;
        days = 80;
        DaySix.readFile(filePath );
        for(int i=0; i < days; i++ ) {
            System.out.println("day: " + i +"\t" + DaySix.getTimerList() );
            DaySix.timerNextDay();
        }
        //System.out.println("Size of the list after " + days +" days is " + DaySix.getTimerList().size() );
        int expected = (filePath.equals(FILE_PATH_TEST))?(5934):((filePath.equals(FILE_PATH_ACTUAL))?(395627):-1);

        Assertions.assertEquals(expected,DaySix.getTimerList().size());

    }

}
