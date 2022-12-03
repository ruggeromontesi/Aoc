package it.ruggero.adventofcode2022.day2.common;

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
        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }
}
