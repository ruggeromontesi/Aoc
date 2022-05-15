/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day4.basesolution.entity;


import it.ruggero.adventofcode2021.day4.basesolution.entity.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void checkNumberTest() {
        int [][] matrix = {
                {22, 13, 17, 11, 0},
                { 2, 23,  4, 24, 8},
                {21,  9, 14, 16, 7},
                { 6, 10,  3, 18, 5},
                {1, 12, 20, 15, 19}
        };

        Board board =  new Board(matrix);
        board.checkNumber(23);
        Assertions.assertEquals(board.getNumbers()[1][1], -1);
    }

    @Test
    public void checkBingoTest() {
        int [][] matrix = {
                {14, 21, 17, 24,  4},
                {10, 16,  15, 9, 19},
                {18,  8, 23, 26, 20},
                {22, 11, 13,  6,  5},
                { 2,  0, 12,  3,  7}
        };

        Board board =  new Board(matrix);
        int[] poppingUpNumbers = {7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1};
        for( int poppedUpNumber : poppingUpNumbers) {
            board.checkNumber(poppedUpNumber);
            if (board.checkBingo()) {
                Assertions.assertEquals(24,poppedUpNumber);
                return;
            }
        }
    }

    @Test
    public void checkCalculateScoreTest() {
        int [][] matrix = {
                {14, 21, 17, 24,  4},
                {10, 16,  15, 9, 19},
                {18,  8, 23, 26, 20},
                {22, 11, 13,  6,  5},
                { 2,  0, 12,  3,  7}
        };

        Board board =  new Board(matrix);
        int[] poppingUpNumbers = {7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1};
        for( int poppedUpNumber : poppingUpNumbers) {
            board.checkNumber(poppedUpNumber);
            if (board.checkBingo()) {
                board.calculateScore(poppedUpNumber);
                break;
            }
        }

        Assertions.assertEquals(4512,board.getScore());

    }


}
