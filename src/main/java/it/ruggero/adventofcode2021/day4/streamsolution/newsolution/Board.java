package it.ruggero.adventofcode2021.day4.streamsolution.newsolution;


import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final BingoNumber[][] table = new BingoNumber[5][5];
    private BingoSession bingoSession;
    private boolean winning;
    private int finalScore;

    public Board(String boardAsString, BingoSession bingoSession) {
        this.bingoSession = bingoSession;
        String[] numbers = boardAsString.split(" ");
        List<String> numbersAsStrings = new ArrayList<>(Arrays.asList(numbers));

        numbersAsStrings.removeIf(String::isEmpty);
        if (numbersAsStrings.size() != 25) {
            throw new RuntimeException();
        }
        IntStream.range(0, 25).forEach(i ->
                table[i / table[0].length][i % table.length] = bingoSession.retrieveBingoNumberObject(Integer.parseInt(numbersAsStrings.get(i)))
        );
    }

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }

    public BingoNumber[][] getTable() {
        return table;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void checkBoardHorizontallyWithStreams(int lastDrawnNumber) {
        IntStream.range(0, table.length).forEach(rowIndex -> {
            if (IntStream.range(0, table[rowIndex].length)
                    .allMatch(columnIndex -> table[rowIndex][columnIndex].isDrawn())) {
                winning = true;
                calculateScore(lastDrawnNumber);
                bingoSession.setOver(true);
                return;
            }
        });
    }

    public void checkBoardVerticallyWithStreams(int lastDrawnNumber) {
        IntStream.range(0,table[0].length).forEach(columnIndex -> {
            if(IntStream.range(0,table.length).allMatch(rowIndex ->  table[rowIndex][columnIndex].isDrawn())) {
                winning = true;
                calculateScore(lastDrawnNumber);
                bingoSession.setOver(true);
                return;

            }
        });
    }

    public void calculateScore(int lastDrawnNumber) {
        finalScore = 0;
        if (this.winning) {
            finalScore = IntStream.range(0, table.length * table[0].length - 1)
                    .mapToObj(index -> table[index / table.length][index % table[0].length])
                    .filter(bingoNumber -> !bingoNumber.isDrawn())
                    .mapToInt(BingoNumber::getValue).sum() * lastDrawnNumber;
            System.out.println("final score calculated value = " + finalScore);
        }


    }




    public String toString() {
        StringBuilder out = new StringBuilder("\n\n");
        for (int rowIndex = 0; rowIndex < table.length; rowIndex++) {
            out.append("[");
            for (int columnIndex = 0; columnIndex < table[rowIndex].length; columnIndex++) {
                out.append(table[rowIndex][columnIndex].getValue() + " ");
            }
            out.append("]\n");
        }
        out.append("\n\n");

        return out.toString();
    }
}
