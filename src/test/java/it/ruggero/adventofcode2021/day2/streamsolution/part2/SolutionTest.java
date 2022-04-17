package it.ruggero.adventofcode2021.day2.streamsolution.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final static String filePath =  ".\\src\\main\\resources\\day2\\directions.txt";

    @Test
    public void testGetResult() {
            Assertions.assertEquals(2015547716,new Solution(filePath).getResult());
    }

}
