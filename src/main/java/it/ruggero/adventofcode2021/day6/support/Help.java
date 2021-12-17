/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day6.support;

/**
 * @author montesr
 */
public class Help {
    private static int numberOfCycles = 0;
    private static int numberOfFishes = 0;
     //private int level = 0;

    public static void main(String[] args) {
        numberOfFishes++;
        sequence(1,19,0);
        //sequence(4,26,0);
        //sequence(3,26,0);
        //sequence(1,26,0);

        //sequence(2,26,0);
        System.out.println("Number of fishes = " + numberOfFishes);
    }


    public static void sequence(int seed, int numberOfIterations ,int level) {
        boolean generateNewFish = false;

        for(int i = 1; i < numberOfIterations+1; i++) {


            if(generateNewFish) {
                print("new fish" , level);
                numberOfFishes++;
                print("new sequence start" , level);
                sequence(8 , numberOfIterations+1-i, level +1 );
                print("new sequence stop" , level);

            }


            if(seed > 0) {
                print( i + " : \t " +seed , level);
                seed--;
                generateNewFish = false;


            } else {
                print( i + " : \t " +seed , level);
                seed = 6;
                generateNewFish = true;


            }

        }
        System.out.println("end!" );


    }


    private static void print(String message, int level ) {
        for(int i =0; i < level; i++) {
            System.out.print("--------    ");
        }
        System.out.print(message + "\n");

    }
}
