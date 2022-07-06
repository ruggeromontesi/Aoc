package it.ruggero.adventofcode2021.day5.streamsolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HydrotermalVentureMap {

    private final List<Line> lines = new ArrayList<>();
    private final int[][] mapWithNumberOfOverlappingLines;
    private final int totalNumberOfRows;
    private final int totalNumberOfColumns;
    private int numberOfPointsWhereAtLeastTwoLinesOverlap = 0;


    public HydrotermalVentureMap(String filepath) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filepath));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] coordinates = line.split("->");
                lines.add(
                        new Line(
                                new Coordinate(coordinates[0]),
                                new Coordinate(coordinates[1]))
                );
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!!!");
        }

        totalNumberOfColumns = lines.stream().mapToInt(line -> {
             return Math.max(line.getInitialCoordinate().getX(), line.getFinalCoordinate().getX());
        }).max().getAsInt() + 1;

        totalNumberOfRows = lines.stream().mapToInt(line -> {
            return Math.max(line.getInitialCoordinate().getY(), line.getFinalCoordinate().getY());
        }).max().getAsInt() + 1;

        mapWithNumberOfOverlappingLines = new int[totalNumberOfRows][totalNumberOfColumns];

    }

    public List<Line> getLines() {
        return lines;
    }

    public int getTotalNumberOfRows() {
        return totalNumberOfRows;
    }

    public int getTotalNumberOfColumns() {
        return totalNumberOfColumns;
    }

    public int getNumberOfPointsWhereAtLeastTwoLinesOverlap() {
        return numberOfPointsWhereAtLeastTwoLinesOverlap;
    }

    public int[][] getMapWithNumberOfOverlappingLines() {
        return mapWithNumberOfOverlappingLines;
    }

    public void createMapWitNumberOfOverlappingLines() {
        for(int rowIndex = 0; rowIndex < totalNumberOfRows; rowIndex++) {
            for(int columnIndex = 0; columnIndex < totalNumberOfColumns; columnIndex++) {
                Coordinate coordinate = new Coordinate(columnIndex,rowIndex);
                int count = mapWithNumberOfOverlappingLines[rowIndex][columnIndex] = (int) lines.stream()
                        .filter(line -> line.containsCoordinate(coordinate))
                        .count();
                if (count > 1) {
                    numberOfPointsWhereAtLeastTwoLinesOverlap++ ;
                }
            }
        }
    }


}
