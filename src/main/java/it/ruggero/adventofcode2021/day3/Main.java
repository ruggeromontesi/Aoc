package it.ruggero.adventofcode2021.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static  int SIZE ;
    //private static  String filePath = ".\\src\\main\\resources\\day3\\test.txt";
    private static  String filePath = ".\\src\\main\\resources\\day3\\report.txt";
    private static String[] readings ;
    private static int stringLength ;

    public static int preliminaryScan( ){
        int i = 0;
        Scanner scanner;
        int tmp = 0;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {

                if ( i == 0) {
                    tmp = scanner.nextLine().length();
                } else {
                    if ( tmp != scanner.nextLine().length()) {
                        throw  new RuntimeException();
                    }
                }
                i++;
            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }
        stringLength = tmp;
        SIZE = i;
        System.out.println("result is  " + i);
        readings = new String[SIZE];
        return  i;
    }


    public static void readFile() {
        int i = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                 readings[i++]=scanner.nextLine();

            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }

    }

    public static void readFile(String filePathVariable) {
        filePath = filePathVariable;
        readFile();


    }

    public static int gammaRate() {
        String output ="";

        for(int charIndex = 0; charIndex < stringLength ; charIndex++) {
            int numberOfZero = 0;
            int numberOfOne = 0;
            for( int i = 0; i< SIZE; i++) {
                if ( readings[i].charAt(charIndex) == '0') numberOfZero++;
                if ( readings[i].charAt(charIndex) == '1') numberOfOne++;
            }
            if (numberOfOne + numberOfZero != SIZE) {
                throw  new RuntimeException();
            }

            if(numberOfZero > numberOfOne ) {
                output += "0";
            }
            if(numberOfZero < numberOfOne ) {
                output += "1";
            }



        }
        if (output.length() != stringLength) {
            throw  new RuntimeException("La Stringa di uscita ha lunghezza diversa dalle stringhe di ingresso!");
        }
        return binaryStringToDecimal(output );
    }

    public static int epsilonRate() {
        String output ="";

        for(int charIndex = 0; charIndex < stringLength ; charIndex++) {
            int numberOfZero = 0;
            int numberOfOne = 0;
            for( int i = 0; i< SIZE; i++) {
                if ( readings[i].charAt(charIndex) == '0') numberOfZero++;
                if ( readings[i].charAt(charIndex) == '1') numberOfOne++;
            }
            if (numberOfOne + numberOfZero != SIZE) {
                throw  new RuntimeException();
            }

            if(numberOfZero > numberOfOne ) {
                output += "1";
            }
            if(numberOfZero < numberOfOne ) {
                output += "0";
            }



        }
        if (output.length() != stringLength) {
            throw  new RuntimeException("La Stringa di uscita ha lunghezza diversa dalle stringhe di ingresso!");
        }
        return binaryStringToDecimal(output );

    }


    public static int binaryStringToDecimal(String input) {
        int output = 0;
        for(int i = 0; i < input.length(); i++ ) {
            try {
                int value = Integer.parseInt(input.substring(input.length()-1-i, input.length()-i));
                if (value == 1) {
                    output += Math.pow(2, i);
                }
                if ( (value != 0) && (value != 1)) {
                    throw new RuntimeException("la stringa non e binaria!");
                }
            } catch(NumberFormatException ex ) {

            }

        }
        return  output;
    }



}
