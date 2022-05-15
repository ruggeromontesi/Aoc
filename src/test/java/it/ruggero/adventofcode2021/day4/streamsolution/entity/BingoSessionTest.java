package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BingoSessionTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day4\\testBingo.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day4\\bingo.txt";

    @Test
    public void testCreateBingoSession() {
        BingoSession bingoSession = new BingoSession(FILE_PATH_TEST);
        Assertions.assertEquals(3,bingoSession.getBoards().size());
        bingoSession = new BingoSession(FILE_PATH_ACTUAL);
        Assertions.assertEquals(100,bingoSession.getBoards().size());


    }
}
