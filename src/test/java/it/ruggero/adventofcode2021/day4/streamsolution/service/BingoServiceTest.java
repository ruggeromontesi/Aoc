package it.ruggero.adventofcode2021.day4.streamsolution.service;

import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoSession;
import it.ruggero.adventofcode2021.day4.streamsolution.entity.Board;
import it.ruggero.adventofcode2021.day4.streamsolution.entity.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class BingoServiceTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day4\\testBingo.txt";

    @Test
    public void testCheckThisRow(){
        BingoSession bingoSession = new BingoSession(FILE_PATH_TEST);
        BingoService bingoService = new BingoService(bingoSession);
        Board testBoard = bingoSession.getBoardsAtInitialState().get(0);
        IntStream.range(0, bingoSession.NUMBER_OF_COLUMNS_IN_THE_BOARD)
                .mapToObj( colIndex -> new Coordinate(2,colIndex))
                .map(testBoard.getTable()::get ).forEach(bingoNumber -> bingoNumber.setDrawn(true));
        Assertions.assertTrue(bingoService.checkThisRow(2,testBoard));

    }



}
