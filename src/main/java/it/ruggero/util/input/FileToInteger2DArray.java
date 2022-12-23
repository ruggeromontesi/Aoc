package it.ruggero.util.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileToInteger2DArray {

    private final FilePathResolver filePathResolver;

    public FileToInteger2DArray(FilePathResolver filePathResolver) {
        this.filePathResolver = filePathResolver;
    }

    public static String[][] readFile(final String filePath) {
        List<String[]> linesAsStringArrays = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                linesAsStringArrays.add(input.nextLine().split(""));
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        return linesAsStringArrays.toArray(String[][]::new);
    }

    private static int[][] mapToInt(final String filePath){
        return Stream.of(readFile(filePath))
                .map(l ->  Stream.of(l).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList())
                .toArray(int [][]::new);
    }

    public int[][] readSample() {
        return mapToInt(filePathResolver.getSampleInputFilePath());
    }

    public int[][]read() {
        return mapToInt(filePathResolver.getInputFilePath());
    }

}
