package it.ruggero.adventofcode2022.day9;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class RopeBridge {

    private Point head = new Point(0, 0, "H", null, null);

    @Getter
    @Setter
    private Point tail = new Point(0, 0);

    private List<Point> knots = new ArrayList<>();

    @Getter
    private List<MotionInstruction> motionInstructions;

    public RopeBridge(List<MotionInstruction> motionInstructions) {
        this.motionInstructions = motionInstructions;
    }

    private void initialize(){
        knots.add(head);
    }

    public RopeBridge() {
    }

    public Point getHead() {
        return  head;
        //return knots.stream().filter(p -> p.getId().equals("H")).findFirst().orElseThrow();
    }

    public void setHead(Point head) {
        this.head = head;
    }

    @Getter
    private final Set<Point> tailPointList = new HashSet<>();

    public void moveSingleInstruction(MotionInstruction m) {
        for (int i = 0; i < m.getSteps(); i++) {
            moveHead(m.getDirection());
            if (!areHeadAndTailAdjacent()) {
                moveTail();
                tailPointList.add(new Point(tail.x, tail.y));
            }
        }
    }

    public void moveMultipleInstructions(List<MotionInstruction> instructions) {
        instructions.forEach(this::moveSingleInstruction);
    }

    public void moveAllInstructions() {
        tailPointList.add(new Point(tail.x, tail.y));
        motionInstructions.forEach(this::moveSingleInstruction);

    }

    public void moveHead(Direction d) {

        //Point head = getHead();
        switch (d) {
            case U:
                head = new Point(head.x, head.y + 1);
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
        }
    }

    public void moveTail() {
        if (head.x - tail.x == 2 && head.y == tail.y) {
            tail = new Point(tail.x + 1, tail.y);
            return;
        }
        if (head.x - tail.x == -2 && head.y == tail.y) {
            tail = new Point(tail.x - 1, tail.y);
            return;
        }
        if (head.y - tail.y == 2 && head.x == tail.x) {
            tail = new Point(tail.x, tail.y + 1);
            return;
        }
        if (head.y - tail.y == -2 && head.x == tail.x) {
            tail = new Point(tail.x, tail.y - 1);
            return;
        }

        if (abs(head.x - tail.x) + abs(head.y - tail.y) == 3) {
            tail = new Point(tail.x + (int) signum(head.x - (float) tail.x), tail.y + (int) signum(head.y - (float) tail.y));
        }
    }

    private boolean areHeadAndTailAdjacent() {
        return abs(head.x - tail.x) + abs(head.y - tail.y) < 2;
    }

 @Getter
 @Setter
 @EqualsAndHashCode
    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

     public Point(int x, int y, String id, Point previous, Point next) {
         this.x = x;
         this.y = y;
         this.id = id;
         this.previous = previous;
         this.next = next;
     }

     private String id;

        private Point previous;

        private Point next;
    }
}
