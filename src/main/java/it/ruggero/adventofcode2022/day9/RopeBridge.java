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
    private Knot head;

    @Getter
    private Knot tail;

    @Getter
    @Setter
    private List<Knot> knots = new ArrayList<>();

    @Getter
    private final Set<Knot> tailPointList = new HashSet<>();

    @Getter
    private List<MotionInstruction> motionInstructions;

    private int numberOfKnots = 2;

    public RopeBridge(List<MotionInstruction> motionInstructions, int numberOfKnots) {
        this.motionInstructions = motionInstructions;
        this.numberOfKnots = numberOfKnots;
        initialize();
    }

    private void initialize() {
//        if(numberOfKnots == 2){
//            initializeWithTwoKnots();
//        } else {
//            initializeWithMoreThanTwoKnots();
//        }

        initializeWithMoreThanTwoKnots();

    }

    private void initializeWithTwoKnots(){
        initializeHead();
        initializeTail();
        tailPointList.add(new Knot(tail.getX(),tail.getY(),tail.getId()));
    }

    private void initializeTail() {
        tail = new Knot(0, 0, "T");
        knots.add(tail);
    }

    private void initializeWithMoreThanTwoKnots(){
        initializeHead();
        for(int knotIndex = 1; knotIndex < numberOfKnots - 1 ; knotIndex++) {
            knots.add(new Knot(0, 0, knotIndex + ""));
        }
        initializeTail();
        tailPointList.add(new Knot(tail.getX(),tail.getY(),tail.getId()));
    }

    private void initializeHead() {
        head = new Knot(0, 0, "H");
        knots.add(head);
    }

    public void moveAllInstructions() {
        motionInstructions.forEach(this::moveSingleInstruction);
    }

    public void moveSingleInstruction(MotionInstruction m) {
        for (int i = 0; i < m.getSteps(); i++) {
            head.move(m.getDirection());
            knots.stream().filter(k -> !k.getId().equals("H")).forEach(k -> {
                Knot previous = getPrevious(k);
                if (!areTwoKnotsAdjacent(previous, k)) {
                    moveAsTail(k);
                    if(k.getId().equals("T")){
                        tailPointList.add(new Knot(k.getX(), k.getY(), k.getId()));
                    }
                }
             }
            );

        }
    }

    public void moveAsTail(Knot k1) {
        Knot previous = getPrevious(k1);

        if (previous.getX() > k1.getX()) {
            k1.move(R);
        }
        if (previous.getX() < k1.getX()) {
            k1.move(L);
        }
        if (previous.getY() > k1.getY()) {
            k1.move(U);
        }
        if (previous.getY() < k1.getY()) {
            k1.move(D);
        }
    }

    public Knot getPrevious(Knot p){
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

    public Knot getKnotById(String id) {
        return knots.stream().filter(k -> k.getId().equals(id)).findFirst().orElseThrow();
    }

    public boolean areTwoKnotsAdjacent(Knot k1, Knot k2) {
        return abs(k1.getX() - k2.getX()) + abs(k1.getY() - k2.getY()) == 1 ||
                (abs(k1.getX() - k2.getX()) == 1 && abs(k1.getY() - k2.getY()) == 1);
    }

}
