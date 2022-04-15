package it.ruggero.adventofcode2021.day1.streamsolution;


import org.junit.jupiter.api.Test;

public class SolutionTest {
    final String filePath =  "C:\\Projects\\Old\\Ruggero\\adventOfCode2021\\src\\main\\resources\\day1\\depths.txt";

    @Test
    public void testReadFile() {
        Solution solution = new Solution(filePath);
        System.out.println("increases are " + solution.calculateIncreases());
        System.out.println("increases with moving windows are " + solution.CalculateIncreasesWindow());
    }
}
