package it.ruggero.adventofcode2021.day4.basesolution;

import it.ruggero.adventofcode2021.day4.basesolution.BingoSession;
import it.ruggero.adventofcode2021.day4.basesolution.entity.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BingoSessionTest {

    //@Test
    public void testReadFile() {
        String filePath = ".\\src\\main\\resources\\day4\\testBingo.txt";
        BingoSession.readFile(filePath);
        BingoSession.processData();
        for(int a : BingoSession.getCalledNumbers()) {
            System.out.println(a + "\n");
        }

        for(Board board : BingoSession.getBoardList()) {
            System.out.println("\n\n");
            System.out.println("print of board with index " + BingoSession.getBoardList().indexOf(board));
            for(int[] row : board.getNumbers()) {
                System.out.println();
                for(int a : row) {
                    System.out.print("\t"+a);
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testPlaySampleConfiguration() {
        String filePath = ".\\src\\main\\resources\\day4\\testBingo.txt";
        BingoSession.readFile(filePath);
        BingoSession.processData();
        BingoSession.play();
        Assertions.assertEquals(4512, BingoSession.getFinalScore() );
    }

    @Test
    public void testPlay() {
        String filePath = ".\\src\\main\\resources\\day4\\bingo.txt";
        BingoSession.readFile(filePath);
        BingoSession.processData();
        BingoSession.play();
        Assertions.assertEquals(49686, BingoSession.getFinalScore() );
    }

    @Test
    public void testPlayToLooseSampleConfiguration() {
        String filePath = ".\\src\\main\\resources\\day4\\testBingo.txt";
        BingoSession.readFile(filePath);
        BingoSession.processData();
        BingoSession.playToLoose();
        System.out.print("Final score for play to loose "+ BingoSession.getFinalScore());
        Assertions.assertEquals(1924, BingoSession.getFinalScore() );
    }

    @Test
    public void testPlayToLoose() {
        String filePath = ".\\src\\main\\resources\\day4\\bingo.txt";
        BingoSession.readFile(filePath);
        BingoSession.processData();
        BingoSession.playToLoose();
        System.out.print("Final score for play to loose "+ BingoSession.getFinalScore());
        Assertions.assertEquals(26878, BingoSession.getFinalScore() );
    }



}
