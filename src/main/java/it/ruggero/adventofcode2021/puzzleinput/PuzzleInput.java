/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.puzzleinput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author montesr
 */
public class PuzzleInput {
   private final static int[] depths = new int[2000];
   static {
      readFile();
     // processSimple();
   }

   public static void main(String[] args)  {

      //readFile();
      processSimple();
   }


   private static void readFile()  {
      Scanner scanner;
      int i = 0;
      int numberOfIncreasesIn = 0;
      final String filePath =  "C:\\Projects\\Old\\Ruggero\\adventOfCode2021\\src\\main\\resources\\depths.txt";
      try {
         scanner = new Scanner(new File(filePath));
         System.out.println("scanning the file");
         while (scanner.hasNextInt()) {
            depths[i++] = scanner.nextInt();
            if( (i>1) && (depths[i-1] > depths[i-2])) {
               numberOfIncreasesIn++;
            }
         }
      } catch(FileNotFoundException ex) {
         System.out.println("Exception caught!");
         System.out.println(ex);
      }
      System.out.println("number of increases In " + numberOfIncreasesIn);

   }

   public static int processSimple() {

      int numberOfIncreases = 0;
      for(int i =1 ; i<2000; i++) {
         System.out.println("depths[i] = " + depths[i]);
         if(depths[i] > depths[i-1]) {
            numberOfIncreases++;
         }
      }
      System.out.println("number of increases " + numberOfIncreases);
      return numberOfIncreases;
   }


   public static int processWindow() {
      int depthsMovingWindow[] = new int[depths.length-2];
      int numberOfIncreases = 0;
      List<Integer> windowValues = new ArrayList<>();

      for(int i = 0; i < depthsMovingWindow.length; i++) {
         depthsMovingWindow[i] = depths[i]+depths[i+1]+depths[i+2];
         //windowValues.add(depths[i]+depths[i+1]+depths[i+2]);
      }

      for(int i = 1; i < depthsMovingWindow.length; i++) {
         if(depthsMovingWindow[i] > depthsMovingWindow[i-1]) {
            numberOfIncreases++;
         }

      }

      //System.out.println("number of valus in movingWindow" + windowValues.size());

      System.out.println("number of increases in depthsMovingWindow " + numberOfIncreases);
      return numberOfIncreases;
   }


}
