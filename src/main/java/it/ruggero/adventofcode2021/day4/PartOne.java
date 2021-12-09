/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day4;

import it.ruggero.adventofcode2021.day4.entity.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author montesr
 */
public class PartOne {
    private static  String filePath = ".\\src\\main\\resources\\day4\\bingo.txt";
    private static List<String> strings = new ArrayList<>();
    private static List<Board> boardList = new ArrayList<>();

    public static int[] getCalledNumbers() {
        return calledNumbers;
    }

    public static void setCalledNumbers(int[] calledNumbers) {
        PartOne.calledNumbers = calledNumbers;
    }

    private static int[] calledNumbers;


    public static void readFile() {
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
                while(aux < 5) {
                    Board board = new Board( );
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
                    boardList.add(board);
                    aux++;
                }




            }

        }




    }
}
