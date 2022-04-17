package it.ruggero.adventofcode2021.day2.streamsolution.part1;
import it.ruggero.adventofcode2021.day2.streamsolution.part1.Solution;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final static String filePath =  ".\\src\\main\\resources\\day2\\directions.txt";

    @Test
    public void testSolution() {
        //Solution solution = new Solution(filePath);
        System.out.println( new Solution(filePath).getResult());

    }
}
