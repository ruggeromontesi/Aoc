package it.ruggero.util.input;

import lombok.Getter;

public class FilePathResolver {
    private final int dayNumber;
    private final int yearNumber;

    @Getter
    private String sampleInputFilePath;

    @Getter
    private String inputFilePath;

    public FilePathResolver(int dayNumber, int yearNumber) {
        this.dayNumber = dayNumber;
        this.yearNumber = yearNumber;
        buildSampleInputFilePath();
        buildInputFilePath();
    }


    private void buildSampleInputFilePath() {
        sampleInputFilePath = ".\\src\\main\\resources\\adventofcode" + yearNumber + "\\day" + dayNumber + "\\testDay" + dayNumber + ".txt";
    }

    private void buildInputFilePath() {
        inputFilePath = ".\\src\\main\\resources\\adventofcode" + yearNumber + "\\day" + dayNumber + "\\Day" + dayNumber + ".txt";
    }
}
