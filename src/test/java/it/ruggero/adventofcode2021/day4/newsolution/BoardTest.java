package it.ruggero.adventofcode2021.day4.newsolution;

import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day4\\testBingo.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day4\\bingo.txt";
    private static final String FILE_PATH_SINGLEBOARD = ".\\src\\main\\resources\\day4\\singleboard.txt";

    @Test
    public void testCreateBingoSessionSingleBoard() {
        BingoSession bingoSession = new BingoSession(FILE_PATH_SINGLEBOARD);
        bingoSession.getBoardsAtInitialState().get(0);
    }

    @Test
    public void testCreateBingoSessionMultipleBoards() {
        BingoSession bingoSession = new BingoSession(FILE_PATH_ACTUAL);
        int numberOfBoards = bingoSession.getBoardsAtInitialState().size();
        //Assertions.assertEquals(3,numberOfBoards);
    }

    @Test
    public void testBingoSessionTestCase() {
        BingoSession bingoSession = new BingoSession(FILE_PATH_TEST);
        bingoSession.playSession();
        System.out.println("finalScore = " + bingoSession.getFinalScore());
        Assertions.assertEquals(4512, bingoSession.getFinalScore() );
    }

    @Test
    public void testBingoSessionCompleteCase() {
        BingoSession bingoSession = new BingoSession(FILE_PATH_ACTUAL);
        bingoSession.playSession();
        System.out.println("finalScore = " + bingoSession.getFinalScore());
        Assertions.assertEquals(49686, bingoSession.getFinalScore() );
    }
}
