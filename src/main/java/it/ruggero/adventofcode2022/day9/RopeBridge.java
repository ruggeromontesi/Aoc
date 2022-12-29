package it.ruggero.adventofcode2022.day9;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.signum;

public class RopeBridge {
    private String filePath;
    @Getter
    @Setter
    private Point head = new Point(0,0);
    @Getter
    @Setter
    private Point tail =new Point(0,0);

    @Getter
    private List<MotionInstruction> motionInstructions;

    public RopeBridge(List<MotionInstruction> motionInstructions) {
        this.motionInstructions = motionInstructions;
    }

    public RopeBridge() {
    }

    @Getter
    private Set<Point> tailPointList = new HashSet<>();

    public void moveSingleInstruction(MotionInstruction m) {

        for(int i = 0; i < m.getSteps(); i ++) {
            moveHead(m.getDirection());

            moveTail();
        }
    }

    public void moveMultipleInstructions(List<MotionInstruction> instructions) {
        instructions.forEach(this::moveSingleInstruction);
    }

    public void moveAllInstructions() {
        tailPointList.add(new Point(tail.x, tail.y));
        for(int i = 0; i < motionInstructions.size(); i ++) {
            moveSingleInstruction(motionInstructions.get(i));
        }
    }

    public void moveHead(Direction d) {
        switch (d) {
            case U:
                head =  new Point(head.x, head.y + 1);
                break;
            case D:
                head = new Point(head.x, head.y - 1);
                break;
            case L:
                head = new Point(head.x - 1, head.y);
                break;
            case R:
                head = new Point(head.x + 1, head.y);
                break;
            default:throw  new RuntimeException();
        }
    }
    
    public void moveTail() {

        if (Math.abs(head.x - tail.x) + Math.abs(head.y - tail.y) < 2) {
            return;
        }

        if (Math.abs(head.x - tail.x) == 1 && Math.abs(head.y - tail.y) == 1) {
            return;
        }

       //tail = new Point(tail.x + (int)signum(head.x - (float)tail.x), tail.y + (int)signum(head.y - (float)tail.y));


        if (head.x - tail.x == 2 && head.y == tail.y) {
            tail = new Point(tail.x + 1, tail.y);
            tailPointList.add(new Point(tail.x, tail.y));
            return;
        }
        if (head.x - tail.x == -2 && head.y == tail.y) {
            tail = new Point(tail.x - 1,  tail.y);
            tailPointList.add(new Point(tail.x, tail.y));
            return;
        }
        if (head.y - tail.y == 2 && head.x == tail.x) {
            tail = new Point(tail.x, tail.y + 1);
            tailPointList.add(new Point(tail.x, tail.y));
            return;
        }
        if (head.y - tail.y == -2 && head.x == tail.x) {
            tail = new Point(tail.x, tail.y - 1);
            tailPointList.add(new Point(tail.x, tail.y));
            return;
        }

        if(Math.abs(head.x - tail.x ) + Math.abs(head.y - tail.y) == 3) {
            tail = new Point(tail.x + (int)signum(head.x - tail.x),tail.y + (int)signum(head.y - tail.y));
            tailPointList.add(new Point(tail.x, tail.y));

        }


    }

    private boolean areHeadAndTailAdjacent(){
        return Math.abs(head.x - tail.x ) + Math.abs(head.y - tail.y) < 2;

    }
    
    
    
    
    @With
    @Data
    @Builder
    static class Point {

        private int x;
        private int y;
    }
    

    

}
