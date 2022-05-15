package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoSession {

    private final int NUMBER_OF_ROWS_IN_THE_BOARD = 5;

    private List<Board> boards = new ArrayList<>();
    private List<Integer> drawnNumbers = new ArrayList<>();

    public BingoSession(String filePath) {
        List<String> strings;

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
                    createRow(rowBoardNumbersAsString,board,numberOfConsecutiveLines++);
                } else if(line.length() == 0) {
                    numberOfConsecutiveLines = 0;
                    board = new Board();
                }


            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }

        //System.out.println("number of acquired lines " + strings.size());

    }

    public List<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }


    private void createRow(String[] integersAsString, Board board, int rowIndex) {
        Map<Coordinate, BingoNumber> map = board.getTable();
        int tableColIndex = 0;
        for (int colIndex = 0; colIndex < integersAsString.length; colIndex++ ) {
            String integerAsString = integersAsString[colIndex];
            Scanner scanner = new Scanner(integerAsString.trim());
            if (scanner.hasNextInt()) {
                BingoNumber bingoNumber = new BingoNumber(scanner.nextInt());
                Coordinate coordinate = new Coordinate(rowIndex,tableColIndex);
                map.put(coordinate, bingoNumber);
                tableColIndex++;
            }
        }

        if ( rowIndex == NUMBER_OF_ROWS_IN_THE_BOARD - 1) {
            boards.add(board);
            board = new Board();
        }
    }


}
