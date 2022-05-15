package it.ruggero.adventofcode2021.day4.basesolution.entity;

public class Board {
    private int numbers[][] = new int[5][5];
    private int score = 0;

    public Board(int[][] numbers) {
        this.numbers = numbers;
    }

    public Board() {
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public int getScore() {
        return score;
    }

    public void checkNumber(int number) {
        for(int i=0; i< numbers.length; i++){
            for(int j =0; j < numbers[i].length; j++) {
                if ( numbers[i][j] == number ) {
                    numbers[i][j] = -1;
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


    public void calculateScore(int lastPoppeedUpNumber ){
        for(int[] row : numbers ) {
            for(int number : row) {
                score = (number == -1)?(score):(score+number);
            }
        }
        score *= lastPoppeedUpNumber;
    }

    public void print( ){
        System.out.println();
        for(int[] row : numbers){
            System.out.print("\n[");
            for(int number: row) {
                System.out.print( " "+ number);
            }
            System.out.print("]");

        }
        System.out.println();
    }

    public String toString( ){
        String output = "\n";
        for(int[] row : numbers){
            output += "\n[";

            for(int number: row) {
                output +=  " "+ number;

            }
            output += "]";


        }
        output += "\n";
        return output;
    }




}
