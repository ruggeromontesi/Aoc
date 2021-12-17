/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day6.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

//C:\Projects\Old\Ruggero\adventOfCode2021\src\main\resources\day6\fishes.txt


//SOLUTION BY TELESOFTAS!!!!
public class Lanternfish {
    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("fishes.txt")) {
            int[] fishes = toNumbersArray(input);

            System.out.println("How many lanternfish would there be after 80 days?");
            System.out.println(amountOfLanternFishAfter(80, fishes));

            System.out.println("How many lanternfish would there be after 256 days?");
            System.out.println(amountOfLanternFishAfter(256, fishes));
        }
    }

    public static long amountOfLanternFishAfter(int days, int[] fishes) {
        long[] amounts = new long[days];
        for (int fish : fishes) {
            fishReplication(fish, days, amounts, 1);
        }
        for (int i = 0; i < amounts.length; i++) {
            if (amounts[i] > 0) {
                fishReplication(i + 9, days, amounts, amounts[i]);
            }
        }
        return fishes.length + Arrays.stream(amounts).sum();
    }

    public static void fishReplication(int fishTimer, int days, long[] amounts, long increment) {
        for (int i = fishTimer; i < days; i += 7) {
            amounts[i] += increment;
        }
    }

    static int[] toNumbersArray(InputStream input) {
        return new Scanner(input)
                .useDelimiter(",")
                .tokens()
                .mapToInt(Integer::valueOf)
                .toArray();
    }

}
