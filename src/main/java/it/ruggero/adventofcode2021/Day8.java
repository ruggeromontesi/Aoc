
package it.ruggero.adventofcode2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8 {
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day8\\day8.txt";
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day8\\testDay8.txt";

    public static void main(String[] args) {
        String filePath = FILE_PATH_ACTUAL;
        readFile(filePath);
    }


    public static void readFile(String filePath) {
        String line;
        int counter = 0;

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();
                String[] digits = line.substring(line.indexOf('|')).trim().split(" ");
                for(String string : digits) {
                    if (string.length() == 2 || string.length() == 3 || string.length() == 4 || string.length() == 7) {
                        counter++;
                    }
                }
            }

        } catch(FileNotFoundException ex) {
            System.out.println( ex );
        }
        System.out.println(" counter " + counter);

    }

}
