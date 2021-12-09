/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day4.entity;

/**
 * @author montesr
 */
public class Board {
    private int numbers[][] = new int[5][5];

    public int[][] getNumbers() {
        return numbers;
    }

    public void checkNumber(int number) {
        for(int[] row : numbers ) {
            for(int a: row) {
                if ( a == number ) {
                    a = -1;
                }
            }
        }
    }

    public boolean checkBingo() {
        boolean rowCheck ;
        boolean columnCheck ;
        for(int i = 0; i <5; i++ ) {
            rowCheck = true;
            for(int j = 0; j <5; j++ ) {
                 if(numbers[i][j] != -1 ) {
                    rowCheck =false;
                }
            }
            if(rowCheck) {
                return rowCheck;
            }
        }

       for(int i = 0; i <5; i++ ) {
            columnCheck = true;
            for(int j = 0; j <5; j++ ) {
                if(numbers[j][i] != -1 ) {
                    columnCheck =false;
                }

            }
            if(columnCheck) {
                return columnCheck;
            }
        }
       return false;
    }


}
