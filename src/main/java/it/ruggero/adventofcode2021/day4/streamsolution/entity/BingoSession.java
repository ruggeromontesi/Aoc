package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import it.ruggero.adventofcode2021.day4.streamsolution.newsolution.BingoNumber;
import it.ruggero.adventofcode2021.day4.streamsolution.newsolution.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoSession {


    private List<Board> boardsAtInitialState = new ArrayList<>();
    private List<Board> boards = new ArrayList<>();
    private List<Integer> drawnNumbers = new ArrayList<>();
    private Map<Integer, List<Board>> sessionHistory = new TreeMap<>();
    private  boolean isOver = false;

    private Set<BingoNumber> allBingoNumbers = new TreeSet<>(Comparator.comparingInt(BingoNumber::getValue));


    {
        IntStream.range(0,100).forEach( i ->
            allBingoNumbers.add(new BingoNumber(i))
        );
    }

    public BingoSession(String filePath) {
        int dummy = allBingoNumbers.size();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            int numberOfConsecutiveLines = 0;
            int rowCounter = 0;
            String boardAsString = "";
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.length() > -1 && line.contains(",")) {
                    String[] drawnNumbersAsString = line.split(",");
                    drawnNumbers = Arrays.asList(drawnNumbersAsString).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                    line = scanner.nextLine();
                    continue;
                }

                if (line.length() > 1 && line.contains(" ") && rowCounter < 5) {
                    rowCounter++;
                    boardAsString += line + " ";
                    if (rowCounter == 5 ) {
                        boardsAtInitialState.add(new Board(boardAsString, this));
                        boardAsString = "";
                        rowCounter = 0;
                    }

                } else if(line.length() == 0) {

                }
            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }
        boards = new ArrayList<>(boardsAtInitialState);

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

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public BingoNumber retrieveBingoNumberObject(int  i) {
        BingoNumber bingoNumber = null;
        try{
            bingoNumber =  allBingoNumbers.stream().filter(bn -> bn.getValue() == i).collect(Collectors.toList()).get(0);
        } catch(IndexOutOfBoundsException ex) {
            System.out.println("problems");
        }

        return bingoNumber;

   }


    public void callSingleNumberOnAllTheBoards(int drawnNumber){
        if(!isOver) {
            retrieveBingoNumberObject(drawnNumber).setDrawn(true);
            List<Board> boardsAtCurrentState = sessionHistory.get(sessionHistory.size() - 1);
            boardsAtCurrentState.stream().forEach(board -> {
                board.checkBoardHorizontallyWithStreams(drawnNumber);
                board.checkBoardVerticallyWithStreams(drawnNumber);
            });

        }
     }

    public void playSession(){

        drawnNumbers.stream().forEach(drawnNumber -> {
            if(!isOver) {
                callSingleNumberOnAllTheBoards(drawnNumber);

            }
            List<Board> boardsAtCurrentState = sessionHistory.get(sessionHistory.size() - 1);
            sessionHistory.put(sessionHistory.size(),boardsAtCurrentState);
        });



    }

    public int getFinalScore() {
        if(isOver) {
            List<Board> lastBoards = sessionHistory.get(sessionHistory.size()-1);
            return lastBoards.stream().filter(Board::isWinning).collect(Collectors.toList()).get(0).getFinalScore();
        } else {
            return  -1;
        }
    }







}
