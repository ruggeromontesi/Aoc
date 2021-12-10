/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day5;

import it.ruggero.adventofcode2021.day5.entity.Coordinate;
import it.ruggero.adventofcode2021.day5.entity.Line;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DayFive {
    private static final String filePathTest = "C:\\Projects\\Old\\Ruggero\\adventOfCode2021\\src\\main\\resources\\day5\\day5test.txt";

    private static List<String> strings = new ArrayList<>();
    private static List<Line> lineList = new ArrayList<>();
    private static  int pointsFinal = 0;

    public static int getPointsFinal() {
        return pointsFinal;
    }

    public static  void  readFile(String filePath) {

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] coordinates = line.split("->");
                Coordinate coordinate1 = new Coordinate(coordinates[0].trim());
                Coordinate coordinate2 = new Coordinate(coordinates[1].trim());

                lineList.add(new Line(coordinate1,coordinate2 ));
                ;
            }
        } catch (FileNotFoundException ex ) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }
    }

    public static List<Line> getLineList() {
        return lineList;
    }

    public static void setLineList(List<Line> lineList) {
        DayFive.lineList = lineList;
    }

    public static void processOnlyHorizontalAndVertical( ){
        int N =1000;

        String map[][] = new String[N][N];
        int points =0;
        List<Line> lineToProcess = lineList.stream().filter(l ->(l.isHorizontal() || l.isVertical())).collect(Collectors.toList());

        for(int i =0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                int counter = 0;
                for(Line line : lineToProcess ) {
                    if ( !line.isVertical() && !line.isHorizontal() ){
                        continue;
                    }

                    if (line.getCoordinateList().contains(new Coordinate(i, j))) {
                        counter++;
                    }
                    if(counter != 0) {
                        map[j][i] = ""+counter;
                    } else {
                        map[j][i] = ".";
                    }
                }
                }

            }

        for(int i = 0; i< N ; i++) {
            System.out.println();
            for(int j = 0; j< N ; j++) {
                System.out.print( map[i][j] + " ");
                if (map[i][j] .equals(".") || map[i][j] .equals("1"))  {
                    continue;
                } else {
                    int aux = Integer.parseInt(map[i][j]);
                    if (aux > 1) {
                        points++;
                    }
                }

            }
        }
        System.out.println("\npoints = " +points);
        pointsFinal = points;


    }

    public static void processAll() {
        System.out.println("processAll()");
        int N =1000;

        String map[][] = new String[N][N];

        for(int i =0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                int counter = 0;
                for(Line line : lineList) {
                    //if (line.containsThisCoordinate(new Coordinate(j,i))) {
                        if (    line.getCoordinateList().contains(new Coordinate(i, j))) {
                        counter++;
                    }
                    if(counter != 0) {
                        map[i][j] = ""+counter;
                    } else {
                        map[i][j] = ".";
                    }
                }
            }

        }

        int points =0;
        for(int i = 0; i< N ; i++) {
            System.out.println();
            for(int j = 0; j< N ; j++) {
                System.out.print( map[i][j] + " ");
                if (map[i][j] .equals(".") || map[i][j] .equals("1"))  {
                    continue;
                } else {
                    int aux = Integer.parseInt(map[i][j]);
                    if (aux > 1) {
                        points++;
                    }
                }

            }
        }
        System.out.println("\npoints = " +points);
        pointsFinal = points;


    }



    public static void printLines() {
        strings.stream().forEach(System.out::println);
    }
}




