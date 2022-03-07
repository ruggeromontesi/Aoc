package it.ruggero.adventofcode2021.day9.standardsolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {



    /*public static void readFile(String filePath) {
        String line;
        int counter = 0;

        int gridMaxX = 0;

        int lineCount = 0;

        Grid grid = new Grid();


        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();

                if (gridMaxX == 0) {
                    gridMaxX = line.length();
                    grid.setGridMaxX(gridMaxX);
                } else {
                    if (gridMaxX != line.length()) {
                        throw new RuntimeException("Inconsistent map!");
                    }
                }

                for (int i = 0; i < line .length(); i++) {
                    int height = 0;
                    try {
                        height = Integer.parseInt(line.substring(i, i+1));
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong map!");
                    }

                    grid.getPoints().add(new Point(i,lineCount,height));



                }

                lineCount++;


            }

            grid.setGridMaxY(lineCount);

        } catch(FileNotFoundException ex) {
            System.out.println( ex );
        }

        //DisplayMapping.getDigits().stream().forEach(System.out::println);
    }
*/
}
