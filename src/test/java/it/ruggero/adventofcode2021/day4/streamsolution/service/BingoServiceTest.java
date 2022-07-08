package it.ruggero.adventofcode2021.day4.streamsolution.service;

import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoSession;

public class BingoServiceTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day4\\testBingo.txt";

    private BingoSession bingoSession = new BingoSession(FILE_PATH_TEST);
    private BingoService bingoService = new BingoService(bingoSession);

//    @Test
//    public void testCheckThisRow(){
//        Board testBoard = bingoSession.getBoardsAtInitialState().get(0);
//        IntStream.range(0, bingoSession.NUMBER_OF_COLUMNS_IN_THE_BOARD)
//                .mapToObj( colIndex -> new Coordinate(2,colIndex))
//                .map(testBoard.getTable()::get ).forEach(bingoNumber -> bingoNumber.setDrawn(true));
//        Assertions.assertTrue(bingoService.checkThisRow(2,testBoard));
//
//    }
//
//    @Test
//    public void testHorizontallyCheckIfThisBoardHasWon() {
//        BingoSession bingoSession = new BingoSession(FILE_PATH_TEST);
//        BingoService bingoService = new BingoService(bingoSession);
//        Board testBoard = bingoSession.getBoardsAtInitialState().get(0);
//        IntStream.range(0, bingoSession.NUMBER_OF_COLUMNS_IN_THE_BOARD)
//                .mapToObj( colIndex -> new Coordinate(2,colIndex))
//                .map(testBoard.getTable()::get ).forEach(bingoNumber -> bingoNumber.setDrawn(true));
//
//        Assertions.assertTrue(bingoService.horizontallyCheckIfThisBoardHasWon(testBoard));
//
//    }
//
//
//    @Test
//    public void testVerticallyCheckIfThisBoardHasWon() {
//        BingoSession bingoSession = new BingoSession(FILE_PATH_TEST);
//        BingoService bingoService = new BingoService(bingoSession);
//        Board testBoard = bingoSession.getBoardsAtInitialState().get(0);
//        IntStream.range(0, bingoSession.NUMBER_OF_ROWS_IN_THE_BOARD)
//                .mapToObj( rowIndex -> new Coordinate(rowIndex,2))
//                .map(testBoard.getTable()::get ).forEach(bingoNumber -> bingoNumber.setDrawn(true));
//        Assertions.assertTrue(bingoService.verticallyCheckIfThisBoardHasWon(testBoard));
//
//    }



}
