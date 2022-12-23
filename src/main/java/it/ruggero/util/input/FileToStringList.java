package it.ruggero.util.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToStringList {

    private final FilePathResolver filePathResolver;

    public FileToStringList(FilePathResolver filePathResolver) {
        this.filePathResolver = filePathResolver;
    }

    protected List<String> readFile(final String filePath) {
        List<String> lines = new ArrayList<>();
        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return lines;
    }

    public List<String> readSample() {
        return readFile(filePathResolver.getSampleInputFilePath());
    }

    public List<String> read() {
        return readFile(filePathResolver.getInputFilePath());
    }


}
