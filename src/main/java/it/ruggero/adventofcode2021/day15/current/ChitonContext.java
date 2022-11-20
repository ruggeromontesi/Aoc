package it.ruggero.adventofcode2021.day15.current;

import it.ruggero.adventofcode2021.day15.old.readfile.ParseFile;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

import java.util.List;

public class ChitonContext {

    @Getter
    private static int WIDTH;
    @Getter
    private static int HEIGHT;

    private static int[][]  cavernMap;


    public static void buildFromFile(String filePath) {
        List<String> lines = (new ParseFile(filePath)).getLines();
        cavernMap = new int[HEIGHT][WIDTH];
        WIDTH = lines.get(0).length();
        HEIGHT = lines.size();

        for (int rowNumber = 0; rowNumber < HEIGHT; rowNumber++) {
            for (int colNumber = 0; colNumber < WIDTH; colNumber++) {
                //cavernMap.put(new it.ruggero.adventofcode2021.day15.old.domain.ChitonContext.Coordinate(rowNumber, colNumber), Integer.parseInt(lines.get(rowNumber).substring(colNumber, colNumber + 1)));
                cavernMap[rowNumber][colNumber] = Integer.parseInt(lines.get(rowNumber).substring(colNumber, colNumber + 1));
            }
        }

    }



    @Data
    @RequiredArgsConstructor
    @With
    public static class Coordinate implements Comparable<Coordinate> {

        private int row;
        private int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Coordinate o) {
            return (this.row - o.row) != 0 ? (this.row - o.row) : (this.col - o.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (row != that.row) return false;
            return col == that.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    public static class Node {
        private Integer riskLevel = Integer.MAX_VALUE;
        private Coordinate coordinate;
        boolean visited = false;
        private Coordinate coordinateOfPreviousNode;

    }
}
