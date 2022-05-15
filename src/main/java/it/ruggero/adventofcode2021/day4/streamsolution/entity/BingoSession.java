package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoSession {
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
                    int finalNumberOfConsecutiveLines = numberOfConsecutiveLines;


                    Board finalBoard = board;
                    IntStream.range(0,rowBoardNumbersAsString.length)
                            .forEach(i -> {
                                try {
                                    int value = Integer.parseInt(rowBoardNumbersAsString[i]);
                                    finalBoard.getTable().put(new Coordinate(i +5 * finalNumberOfConsecutiveLines), new BingoNumber(value));
                                } catch (NumberFormatException ignored) {

                                }


                            });

                    numberOfConsecutiveLines++ ;



                } else if(line.length() == 0) {
                    boards.add(board);
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
}
