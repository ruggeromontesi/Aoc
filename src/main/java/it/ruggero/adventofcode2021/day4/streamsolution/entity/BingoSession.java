package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoSession {

    public final int NUMBER_OF_ROWS_IN_THE_BOARD = 5;

    public final int NUMBER_OF_COLUMNS_IN_THE_BOARD = 5;

    private List<Board> boardsAtInitialState = new ArrayList<>();
    private List<Integer> drawnNumbers = new ArrayList<>();
    private Map<Integer, List<Board>> sessionHistory = new TreeMap<>();

    public BingoSession(String filePath) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            Board board = new Board();
            int numberOfConsecutiveLines = 0;
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.length() > -1 && line.contains(",")) {
                    String[] drawnNumbersAsString = line.split(",");
                    drawnNumbers = Arrays.asList(drawnNumbersAsString).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                    line = scanner.nextLine();
                    continue;
                }

                if (line.length() > 1 && line.contains(" ")) {
                    String[] rowBoardNumbersAsString = line.split(" ");
                    //createRow(rowBoardNumbersAsString,board,numberOfConsecutiveLines++);
                    createRowWithStream(rowBoardNumbersAsString,board,numberOfConsecutiveLines++);
                } else if(line.length() == 0) {
                    numberOfConsecutiveLines = 0;
                    board = new Board();
                }
            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }

        sessionHistory.put(0,boardsAtInitialState);
    }

    public List<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    public List<Board> getBoardsAtInitialState() {
        return boardsAtInitialState;
    }

    public Map<Integer, List<Board>> getSessionHistory() {
        return sessionHistory;
    }

    private void createRow(String[] integersAsString, Board board, int rowIndex) {
        Map<Coordinate, BingoNumber> map = board.getTable();
        int tableColIndex = 0;
        for (String integerAsString : integersAsString) {
            Scanner scanner = new Scanner(integerAsString.trim());
            if (scanner.hasNextInt()) {
                BingoNumber bingoNumber = new BingoNumber(scanner.nextInt());
                Coordinate coordinate = new Coordinate(rowIndex, tableColIndex);
                map.put(coordinate, bingoNumber);
                tableColIndex++;
            }
        }

        if ( rowIndex == NUMBER_OF_ROWS_IN_THE_BOARD - 1) {
            boardsAtInitialState.add(board);
            board = new Board();
        }
    }

    private void createRowWithStream(String[] integersAsString, Board board, int rowIndex) {
        Map<Coordinate, BingoNumber> map = board.getTable();
        IntStream.range(0,integersAsString.length).forEach(colIndex -> {
            Scanner scanner = new Scanner(integersAsString[colIndex].trim());
            if (scanner.hasNextInt()) {
                map.put(new Coordinate(rowIndex, map.size()), new BingoNumber(scanner.nextInt()));
            }
        });

        if ( rowIndex == NUMBER_OF_ROWS_IN_THE_BOARD - 1) {
            boardsAtInitialState.add(board);
            board = new Board();
        }
    }

}
