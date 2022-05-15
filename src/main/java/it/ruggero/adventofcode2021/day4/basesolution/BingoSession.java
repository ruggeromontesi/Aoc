package it.ruggero.adventofcode2021.day4.basesolution;

import it.ruggero.adventofcode2021.day4.basesolution.entity.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BingoSession {
    private static  String filePath = ".\\src\\main\\resources\\day4\\testBingo.txt";
    private static List<String> strings = new ArrayList<>();
    private static List<Board> boardList = new ArrayList<>();
    private static List<Board> winningBoardList = new ArrayList<>();
    private static int[] calledNumbers;
    private static int sessionFinalScore;

    public static List<Board> getBoardList() {
        return boardList;
    }

    public static List<Board> getWinningBoardList() {
        return winningBoardList;
    }

    public static int[] getCalledNumbers() {
        return calledNumbers;
    }

    public static int getFinalScore() {
        return sessionFinalScore;
    }

    public static void readFile( ){
        readFile();
    }


    public static void readFile(String filePath) {
        int i = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > -1) {
                    strings.add(line);
                }


            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }

        System.out.println("number of acquired lines " + strings.size());
    }


    public static void processData( ){
        String calledNumbersString = strings.get(0);
        String[] calledNumbersAsArrayOfStrings = calledNumbersString.split(",");
        calledNumbers = new int[calledNumbersAsArrayOfStrings.length];
        for(int i =0; i < calledNumbersAsArrayOfStrings.length ; i++) {
            try {
                calledNumbers[i] = Integer.parseInt(calledNumbersAsArrayOfStrings[i]);
            } catch(NumberFormatException ex ) {

            }

        }
        int j=1;

        while(j< strings.size())  {
            if ( strings.get(j++).length() == 0) {
                int aux =0;
                Board board = new Board( );
                while(aux < 5) {

                    int[] row = new int[5];
                    String numbersAsStringArray[] = strings.get(j++).split(" ");
                    List<String> processedString = new ArrayList<>();
                    for(String str: numbersAsStringArray ) {
                        if (str.length() != 0) {
                            processedString.add(str);
                        }
                    }
                    numbersAsStringArray = processedString.toArray(new String[0]);

                    if( numbersAsStringArray.length != 5) {
                        throw new RuntimeException("line is not composed of 5 integers");
                    }
                    for(int k =0; k<5; k++) {
                        board.getNumbers()[aux][k] = Integer.parseInt(numbersAsStringArray[k]);
                    }
                    //board.getNumbers()[aux] = row;

                    aux++;
                }
                boardList.add(board);
            }
        }




    }


    public static void play( ){
        for(int calledNumber : calledNumbers){

            for( Board board : boardList){
                board.checkNumber(calledNumber);
                if(board.checkBingo()) {
                    board.calculateScore(calledNumber);
                    sessionFinalScore = board.getScore();
                    System.out.println("final score is " + sessionFinalScore);
                    return;

                }

            }
        }
    }

    public static void playToLoose() {
        List<Board> boardsToCheck = boardList;
        for(int calledNumber : calledNumbers){
            List<Board> boardsToCheckForNextNumber= new ArrayList<>();

            for( Board board : boardsToCheck){

                board.checkNumber(calledNumber);
                if(board.checkBingo()) {
                    board.calculateScore(calledNumber);
                    //System.out.println("this board is winning :");
                    //board.print();
                    sessionFinalScore = board.getScore();
                    //System.out.println("final score is " + sessionFinalScore);
                    winningBoardList.add(board);

                } else {
                    boardsToCheckForNextNumber.add(board);
                    //System.out.println("board : " + board);
                    //System.out.println("boardsToCheckForNextNumber : " + boardsToCheckForNextNumber);
                }

            }

            if( boardsToCheckForNextNumber.size() == 0 ) {
                //System.out.println("no more boards to check!");
                //System.out.println("here the list of winning boards");
                /*for(Board board : winningBoardList) {
                    System.out.println("\n\n");
                    board.print();
                }*/
                sessionFinalScore = winningBoardList.get(winningBoardList.size()-1).getScore();
                boardsToCheck  = new ArrayList<>();
            }


            if( boardsToCheckForNextNumber.size() > 0 ) {
                boardsToCheck = boardsToCheckForNextNumber;
            }





        }

    }






}
