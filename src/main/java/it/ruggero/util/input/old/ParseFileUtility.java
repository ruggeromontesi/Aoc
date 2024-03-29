package it.ruggero.util.input.old;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFileUtility {
    @Getter
    private static final List<String> lines = new ArrayList<>();

    private ParseFileUtility() {
    }

    public static void readFile(final String filePath) {
        lines.clear();
        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }


    public static List<String> getLinesFromFile(final String filePath) {
        readFile(filePath);
        return lines;
    }
}
