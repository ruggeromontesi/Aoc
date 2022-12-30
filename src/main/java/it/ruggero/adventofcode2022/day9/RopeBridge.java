package it.ruggero.adventofcode2022.day9;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static it.ruggero.adventofcode2022.day9.Direction.*;
import static java.lang.Math.abs;

public class RopeBridge {

    @Getter
    private Point head;

    @Getter
    private Point tail;

    @Getter
    @Setter
    private List<Point> knots = new ArrayList<>();

    @Getter
    private final Set<Point> tailPointList = new HashSet<>();

    @Getter
    private List<MotionInstruction> motionInstructions;

    private int numberOfKnots = 2;

    public RopeBridge(List<MotionInstruction> motionInstructions) {
        initialize();
        this.motionInstructions = motionInstructions;
    }

    public RopeBridge(List<MotionInstruction> motionInstructions, int numberOfKnots) {
        this.motionInstructions = motionInstructions;
        this.numberOfKnots = numberOfKnots;
        initialize();
    }

    public RopeBridge(int numberOfKnots) {
        this.numberOfKnots = numberOfKnots;
        initialize();
    }

    public RopeBridge() {
        initialize();
    }

    public void setHead(Point head) {
        this.head.setX(head.getX());
        this.head.setY(head.getY());
        this.head.setId(head.getId());
    }

    public void setTail(Point tail) {
        this.tail.setX(tail.getX());
        this.tail.setY(tail.getY());
        this.tail.setId(tail.getId());
    }

    private void initialize() {
        if(numberOfKnots == 2){
            initializeWithTwoKnots();
        } else {
            initializeWithMoreThanTwoKnots();
        }

    }

    private void initializeWithTwoKnots(){
        initializeHead();
        initializeTail();
        tailPointList.add(new Point(tail.x,tail.y,tail.id));
    }

    private void initializeTail() {
        tail = new Point(0, 0, "T");
        knots.add(tail);
    }

    private void initializeWithMoreThanTwoKnots(){
        initializeHead();
        for(int knotIndex = 1; knotIndex < numberOfKnots - 1 ; knotIndex++) {
            knots.add(new Point(0, 0, knotIndex + ""));
        }
        initializeTail();
    }

    private void initializeHead() {
        head = new Point(0, 0, "H");
        knots.add(head);
    }

    public void moveAllInstructions() {
        motionInstructions.forEach(this::moveSingleInstruction);
    }

    public void moveSingleInstruction(MotionInstruction m) {
        for (int i = 0; i < m.getSteps(); i++) {
            head.move(m.getDirection());
            knots.stream().filter(k -> !k.getId().equals("H")).forEach(k -> {
                Point previous = getPrevious(k);
                if (!areTwoKnotsAdjacent(previous, k)) {
                    moveAsTail(k);
                    if(k.getId().equals("T")){
                        tailPointList.add(new Point(k.x, k.y, k.id));
                    }
                }
             }
            );

        }
    }

    public void moveMultipleInstructions(List<MotionInstruction> instructions) {
        instructions.forEach(this::moveSingleInstruction);
    }

    public void moveAsTail(Point k1) {
        Point previous = getPrevious(k1);

        if (previous.x > k1.x) {
            k1.move(R);
        }
        if (previous.x < k1.x) {
            k1.move(L);
        }
        if (previous.y > k1.y) {
            k1.move(U);
        }
        if (previous.y < k1.y) {
            k1.move(D);
        }
    }

    public Point getPrevious(Point p){
        String stringId = p.getId();

        if(stringId.equals("H")) {
            throw new RuntimeException("called previous on head!");
        }

        if(stringId.equals("T")) {
            return numberOfKnots > 2 ? getKnotById(numberOfKnots - 2 + "") : getKnotById("H") ;
        }

        if(stringId.equals("1")) {
            return getKnotById("H");
        }

        int intId = Integer.parseInt(stringId);
        if(intId < numberOfKnots - 1 && intId > 1) {
            return getKnotById(intId - 1 + "");
        }
        throw new RuntimeException("knot with Id " + stringId + "not existing");

    }

    public Point getKnotById(String id) {
        return knots.stream().filter(k -> k.getId().equals(id)).findFirst().orElseThrow();
    }

    public boolean areTwoKnotsAdjacent(Point k1, Point k2) {
        return abs(k1.x - k2.x) + abs(k1.y - k2.y) == 1 ||
                (abs(k1.x - k2.x) == 1 && abs(k1.y - k2.y) == 1);
    }

    @Data
    static class Point {
        private int x;
        private int y;
        private String id;

        public Point(int x, int y, String id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public void move(Direction d) {
            switch (d) {
                case U:
                    y++;
                    break;
                case D:
                    y--;
                    break;
                case L:
                    x--;
                    break;
                case R:
                    x++;
                    break;
            }
        }

    }
}
