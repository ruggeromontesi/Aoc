package it.ruggero.adventofcode2021.day15.common.readfile;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static it.ruggero.adventofcode2021.day15.common.validate.ValidateInput.validate;

public class ParseFileUtility {

    @Getter
    private  static String[][] valuesAsStringArray;
    private  static int[][] valuesAsIntArray;

    public static void readFile(final String filePath) {
        List<String[]> linesAsStringArrays = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                linesAsStringArrays.add(input.nextLine().split(""));
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        valuesAsStringArray = linesAsStringArrays.toArray(String[][]::new);
    }

    public static void mapToInt(){
        valuesAsIntArray = Stream.of(valuesAsStringArray)
                .map(l ->  Stream.of(l).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList())
                .toArray(int [][]::new);
    }

    public static int[][] parseFileAsIntArray(String filePath) {
        readFile(filePath);
        validate(getValuesAsStringArray());
        mapToInt();
        return valuesAsIntArray;
    }

}
