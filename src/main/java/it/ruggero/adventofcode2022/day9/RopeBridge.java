package it.ruggero.adventofcode2022.day9;

import lombok.Getter;

import java.util.*;

import static it.ruggero.adventofcode2022.day9.Direction.*;
import static java.lang.Math.abs;

public class RopeBridge {
    private final Knot head = new Knot(0, 0, "H");
    private final Knot tail = new Knot(0, 0, "T");
    private final List<Knot> knots = new ArrayList<>();
    @Getter
    private final Set<Knot> tailPointList = new HashSet<>();
    private final List<MotionInstruction> motionInstructions;
    private final int numberOfKnots;

    public RopeBridge(List<MotionInstruction> motionInstructions, int numberOfKnots) {
        this.motionInstructions = motionInstructions;
        this.numberOfKnots = numberOfKnots;
        initialize();
    }

    private void initialize() {
        knots.add(head);
        for (int knotIndex = 1; knotIndex < numberOfKnots - 1; knotIndex++) {
            knots.add(new Knot(0, 0, knotIndex + ""));
        }
        knots.add(tail);
        tailPointList.add(new Knot(tail.getX(), tail.getY(), tail.getId()));
    }

    public void moveAllInstructions() {
        motionInstructions.forEach(this::moveSingleInstruction);
    }

    public void moveSingleInstruction(MotionInstruction m) {
        for (int i = 0; i < m.getSteps(); i++) {
            head.move(m.getDirection());
            knots.stream().filter(k -> !k.getId().equals("H")).forEach(k -> {
                        if (!areTwoKnotsAdjacent(getPrevious(k), k)) {
                            moveAsTail(k);
                            if (k.getId().equals("T")) {
                                tailPointList.add(new Knot(k.getX(), k.getY(), k.getId()));
                            }
                        }
                    }
            );
        }
    }

    public void moveAsTail(Knot k) {
        Knot previous = getPrevious(k);

        if (previous.getX() > k.getX()) { k.move(R);}
        if (previous.getX() < k.getX()) { k.move(L);}
        if (previous.getY() > k.getY()) { k.move(U);}
        if (previous.getY() < k.getY()) { k.move(D);}
    }

    public Knot getPrevious(Knot p) {
        if (p.getId().equals("T")) {
            return numberOfKnots > 2 ? getKnotById(numberOfKnots - 2 + "") : getKnotById("H");
        }
        if (p.getId().equals("1")) {
            return getKnotById("H");
        }
        return getKnotById(Integer.parseInt(p.getId()) - 1 + "");
    }

    public Knot getKnotById(String id) {
        return knots.stream().filter(k -> k.getId().equals(id)).findFirst().orElseThrow();
    }

    public boolean areTwoKnotsAdjacent(Knot k1, Knot k2) {
        return abs(k1.getX() - k2.getX()) + abs(k1.getY() - k2.getY()) == 1 ||
                (abs(k1.getX() - k2.getX()) == 1 && abs(k1.getY() - k2.getY()) == 1);
    }
}
