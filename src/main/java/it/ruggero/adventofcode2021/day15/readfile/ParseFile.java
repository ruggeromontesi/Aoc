package it.ruggero.adventofcode2021.day15.readfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParseFile {

    private final List<String> lines = new ArrayList<>();

    public List<String> getLines() {
        return lines;
    }

    public ParseFile(final String filePath) {
        try {
            Scanner input = new Scanner(new File(filePath));
            int row = 0;
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        System.out.println("A number of " + lines.size() + " lines were acquired");

    }



}
