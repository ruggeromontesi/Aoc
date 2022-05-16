package it.ruggero.adventofcode2021.day4.streamsolution.service;

import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoNumber;
import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoSession;
import it.ruggero.adventofcode2021.day4.streamsolution.entity.Board;
import it.ruggero.adventofcode2021.day4.streamsolution.entity.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BingoService {

    private final BingoSession bingoSession;

    public BingoService(BingoSession bingoSession) {
        this.bingoSession = bingoSession;
    }


    public void drawOneNumber() {
        int currentStepIndex = bingoSession.getSessionHistory().size();
        int currentlyDrawnNumber = bingoSession.getDrawnNumbers().get(currentStepIndex);
        List<Board> lastStepBoardList = bingoSession.getSessionHistory().get(bingoSession.getSessionHistory().size() - 1 );
        List<Board> list = new ArrayList<>(lastStepBoardList);
        list.forEach(board -> checkGivenNumberOnThisBoard(currentlyDrawnNumber,board));
        bingoSession.getSessionHistory().put(bingoSession.getSessionHistory().size(), list);


    }


    public void checkGivenNumberOnThisBoard(int givenNumber,Board board ) {
        board.getTable().entrySet().stream().filter( e -> e.getValue().getValue() == givenNumber).forEach( e -> e.getValue().setDrawn(true));
    }

    public void checkIfThisBoardHasWon(Board board) {

    }

    public boolean horizontallyCheckIfThisBoardHasWon(Board board) {
        return IntStream.range(0,bingoSession.NUMBER_OF_ROWS_IN_THE_BOARD).anyMatch(rowIndex  ->  checkThisRow(rowIndex,board));


    }

   public boolean verticallyCheckIfThisBoardHasWon(Board board) {
        IntStream.range(0, bingoSession.NUMBER_OF_COLUMNS_IN_THE_BOARD).anyMatch(colIndex -> checkThisColumn(colIndex,board));
        return false;
   }



    public boolean checkThisRow(int rowIndex, Board board) {
        return IntStream.range(0, bingoSession.NUMBER_OF_COLUMNS_IN_THE_BOARD).mapToObj(i -> board.getTable().get(new Coordinate(rowIndex,i))).allMatch(BingoNumber::isDrawn);
    }

    public boolean checkThisColumn(int colIndex, Board board) {
        return IntStream.range(0, bingoSession.NUMBER_OF_ROWS_IN_THE_BOARD).mapToObj(i -> board.getTable().get(new Coordinate(i,colIndex))).allMatch(BingoNumber::isDrawn);
    }






}
