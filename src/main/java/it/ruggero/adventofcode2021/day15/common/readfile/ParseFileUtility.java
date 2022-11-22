package it.ruggero.adventofcode2021.day15.common.readfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseFileUtility {

    private final static List<String> lines = new ArrayList<>();

    private  static String[][] valuesAsStringArray;

    private  static int[][] valuesAsIntArray;

    public static List<String> getLines() {
        return lines;
    }



    public ParseFileUtility(final String filePath) {
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        System.out.println("A number of " + lines.size() + " lines were acquired");

    }

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
        List<int[]> linesAsInt = new ArrayList<>();
        for(String[] line : valuesAsStringArray) {
            linesAsInt.add(Stream.of(line).mapToInt(Integer::parseInt).toArray());
        }

        valuesAsIntArray = Stream.of(valuesAsStringArray)
                .map(l ->  Stream.of(l).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList())
                .toArray(int [][]::new);


    }

    public static int[][] parseFileAsIntArray(String filePath) {
        readFile(filePath);
        mapToInt();
        return valuesAsIntArray;
    }

    public static void main(String[] args) {
        String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
        readFile(FILE_PATH_TEST);
    }









}
