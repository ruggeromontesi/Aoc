package it.ruggero.adventofcode2021.day15.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ChitonContext {

    private final int WIDTH;
    private final int HEIGHT;

    //height

    private ChitonContext.Coordinate currentCordinate = new ChitonContext.Coordinate(0,0);

    private final Map<Coordinate,Integer> riskMap  = new TreeMap<>();


    public ChitonContext(List<String> lines) {
        WIDTH = lines.get(0).length();
        HEIGHT = lines.size();
        for(int rowNumber = 0; rowNumber < HEIGHT; rowNumber++) {
            for(int colNumber = 0; colNumber < WIDTH; colNumber++) {
                riskMap.put(new Coordinate(rowNumber,colNumber), Integer.parseInt(lines.get(rowNumber).substring(colNumber,colNumber+1)));
            }
        }

    }

    @Data
    @RequiredArgsConstructor
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

    public void printRiskMap(){
        for(int rowNumber = 0; rowNumber < HEIGHT; rowNumber++) {
            for(int colNumber = 0; colNumber < WIDTH; colNumber++) {
                System.out.print("["+ riskMap.get(new Coordinate(rowNumber,colNumber)) + "]");
            }
            System.out.print("\n");
        }
    }

    public void move(Direction direction) {
//        switch (direction) {
//            case EAST:
//                if (currentCordinate.getCol() < WIDTH -1) {
//                    currentCordinate.setCol(currentCordinate.getCol() + 1);
//                }
//                break;
//
//            case WEST:
//                if (currentCordinate.getCol() > 0) {
//                    currentCordinate.setCol(currentCordinate.getCol() - 1);
//                }
//                break;
//
//            case NORTH:
//                if (currentCordinate.getRow() > 0) {
//                    currentCordinate.setRow(currentCordinate.getRow() - 1);
//                }
//                break;
//
//            case SOUTH:
//                if (currentCordinate.getRow() < HEIGHT - 1) {
//                    currentCordinate.setRow(currentCordinate.getRow() + 1);
//                }
//                break;
//        }

        currentCordinate = move(currentCordinate,direction);

    }

    public Coordinate move(Coordinate startPoint, Direction direction) {

        Coordinate endPoint = new Coordinate(startPoint.getRow(), startPoint.getCol());


        switch (direction) {
            case EAST:
                if (startPoint.getCol() < WIDTH -1) {
                    endPoint.setCol(startPoint.getCol() + 1);
                }
                return  endPoint;

            case WEST:
                if (startPoint.getCol() > 0) {
                    endPoint.setCol(startPoint.getCol() - 1);
                }
                return  endPoint;

            case NORTH:
                if (startPoint.getRow() > 0) {
                    endPoint.setRow(startPoint.getRow() - 1);
                }
                return  endPoint;

            case SOUTH:
                if (startPoint.getRow() < HEIGHT - 1) {
                    endPoint.setRow(startPoint.getRow() + 1);
                }
                return  endPoint;
        }

        return  endPoint;
    }

    public Direction chooseDirection(Coordinate currentCoordinate) {

        Map<Direction,Integer> directionRiskMap = new TreeMap<>();

        for(Direction direction :  Direction.values()){
            var a = move(currentCoordinate,direction);
            if(!a.equals(currentCoordinate)) {
                directionRiskMap.put(direction, riskMap.get(a));
            }

        }

        return directionRiskMap.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).orElseThrow();


    }




    public enum Direction {
        NORTH,EAST,SOUTH,WEST
    }
}


