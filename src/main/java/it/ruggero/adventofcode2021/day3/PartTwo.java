/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author montesr
 */
public class PartTwo {
    //private static  String filePath = ".\\src\\main\\resources\\day3\\test.txt";
    private static  String filePath = ".\\src\\main\\resources\\day3\\report.txt";

    private static List<String> readingsList = new ArrayList<>();

    private static int stringLength;





    private static List<String> selectedReadingsList = new ArrayList<>();

    public static List<String> getReadingsList() {
        return readingsList;
    }
    public static List<String> getSelectedReadingsList() {
        return selectedReadingsList;
    }





    public static void readFile() {


        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                readingsList.add(scanner.nextLine());

            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }
        int tmpStringLength = readingsList.get(0).length();

        for(String reading : readingsList){
            if( reading.length() != tmpStringLength) {
                throw new RuntimeException("readings have different length");
            }
        }

        stringLength = tmpStringLength;
    }

    public static void processList(final int bitIndex,final String criteria ) {
        List<String> listZero  = new ArrayList<>();
        List<String> listOne  = new ArrayList<>();

        if(selectedReadingsList.size()==1) {
            return;
        }

        for(String reading : selectedReadingsList) {
            if (reading.charAt(bitIndex)== '0') listZero.add(reading);
            if (reading.charAt(bitIndex)== '1') listOne.add(reading);
        }

        if(criteria.equals("mostCommonBit")) {
            selectedReadingsList = (listZero.size() == listOne.size() )?(listOne):(( listZero.size() > listOne.size())?(listZero):(listOne));
        }

        if(criteria.equals("leastCommonBit")) {
            selectedReadingsList = (listZero.size() == listOne.size() )?(listZero):(( listZero.size() > listOne.size())?(listOne):(listZero));
        }
    }


    public static int calculateOxygenGeneratorRating() {
        selectedReadingsList = readingsList;
        for(int i = 0; (i< stringLength && selectedReadingsList.size() >1 )  ;i++) {
            processList(i,"mostCommonBit" );
            System.out.println(selectedReadingsList);
        }

        if ( selectedReadingsList.size() == 1) {
            return Main.binaryStringToDecimal(selectedReadingsList.get(0));
        } else {
            throw new RuntimeException("criteria di not give a unique value");
        }
    }



    public static int calculateCO2ScrubberRating() {
        selectedReadingsList = readingsList;
        for(int i = 0; (i< stringLength && selectedReadingsList.size() >1 )  ;i++) {
            processList(i,"leastCommonBit" );
            System.out.println(selectedReadingsList);

        }

        if ( selectedReadingsList.size() == 1) {
           return  Main.binaryStringToDecimal(selectedReadingsList.get(0));
        } else {
            throw new RuntimeException("criteria di not give a unique value");
        }
    }







}
