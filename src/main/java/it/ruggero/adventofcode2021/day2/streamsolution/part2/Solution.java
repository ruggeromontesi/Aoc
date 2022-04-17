package it.ruggero.adventofcode2021.day2.streamsolution.part2;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {


    List<Instruction> instructions = new ArrayList<>();
    public Solution(String filePath) {
        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String[] parts =  scanner.nextLine().split(" ");
                instructions.add(new Instruction(Direction.valueOf(parts[0]),Integer.parseInt(parts[1])));
            }

        } catch(Exception ex) {
        }
    }

    public void processInstruction (Status status,Instruction instruction) {
        switch (instruction.direction) {
            case up :
                status.aim -= instruction.distance;
                break;
            case down:
                status.aim += instruction.distance;
                break;
            case forward:
                status.x += instruction.distance;
                status.y += status.aim* instruction.distance;
                break;
        }
    }

    public int getResult() {
        return instructions.stream().collect(Status::new, this::processInstruction, (r1,r2) -> {return;}).result();
    }

    enum Direction {
        up, down, forward
    }

    class Instruction {
        Direction direction;
        int distance;

        public Instruction(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }
    }

    class Status {
        int x;
        int y;
        int aim;

        public int result() {
            return x*y;
        }
    }

}
