package it.ruggero.adventofcode2021.day2.streamsolution.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private List<Instruction> instructions = new ArrayList<>();

    public Solution(String filePath) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                String parts[] = scanner.nextLine().split(" ");
                instructions.add(new Instruction(Direction.valueOf(parts[0]), Integer.parseInt(parts[1]) ));
            }
        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public Coordinate process( ){
        return instructions.stream().collect(() -> new Coordinate(),Solution::processInstruction, Solution::combine );

    }

    public int getResult() {
        return instructions.parallelStream().collect(() -> new Coordinate(),Solution::processInstruction, Solution::combine ).getArea();

    }


    public static void processInstruction ( Coordinate coordinate, Instruction instruction) {
        if(instruction.getDirection() == Direction.up) {
            coordinate.setY(coordinate.getY()-instruction.getDistance());
        }

        if(instruction.getDirection() == Direction.down) {
            coordinate.setY(coordinate.getY()+instruction.getDistance());
        }

        if(instruction.getDirection() == Direction.forward) {
            coordinate.setX(coordinate.getX()+instruction.getDistance());
        }

    }

    public static void combine(Coordinate coordinate1, Coordinate coordinate2) {
        int x = coordinate1.getX() + coordinate2.getX();
        int y = coordinate1.getY() + coordinate2.getY();
        coordinate1.setX(x);
        coordinate1.setY(y);
        coordinate2.setX(x);
        coordinate2.setY(y);
    }


    enum Direction {
        up,down,forward
    }

    class Instruction {
        private Direction direction;
        private int distance;

        public Instruction(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getDistance() {
            return distance;
        }
    }

    class Coordinate {
        private int x;
        private int y;

        public Coordinate() {
        }

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getArea() {
            return this.x * this.y;
        }



    }
}
