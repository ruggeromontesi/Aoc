package it.ruggero.adventofcode2021.day15.readfile;

import it.ruggero.adventofcode2021.day15.Coordinate;
import it.ruggero.adventofcode2021.day15.Direction;
import it.ruggero.adventofcode2021.day15.Path;
import it.ruggero.adventofcode2021.day15.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class ParseFile {

    private final List<String> lines = new ArrayList<>();

    private  int nColumns;

    private  int nRows;

    public ParseFile(final String filePath) {
        try {
            Scanner input = new Scanner(new File(filePath));
            int row = 0;
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }

            nRows = row - 1 ;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        System.out.println("A number of " + lines.size() + "were acquired");

    }



}
