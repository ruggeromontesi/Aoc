package it.ruggero.adventofcode2021.day3.streamsolution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.IOException;

public class SolutionTest {

    private static  String filePath = ".\\src\\main\\resources\\day3\\report.txt";

    @Test
    public void test() throws IOException {
        Solution solution = new Solution(filePath);
        String result = solution.calculateMostFrequentBit(0);
        System.out.println(result);
        result = solution.calculateGammaRate();
        System.out.println(result);
        //result = solution.calculateEpsilonRate();
        System.out.println(result);

    }

    @Test
    public void testBinarytoInt() throws IOException{
        Solution solution = new Solution(filePath);
        Assertions.assertEquals(1540244,solution.calculatePowerConsumption());

    }
}
