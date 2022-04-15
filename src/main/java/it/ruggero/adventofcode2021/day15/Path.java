package it.ruggero.adventofcode2021.day15;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private Position startingPosition;

    private List<Position> positionList = new ArrayList<>();

    public Position getStartingPosition() {
        return startingPosition;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public Position getLastPosition() {
        if (!positionList.isEmpty()) {
            return positionList.get(positionList.size()-1);
        } else {
            return  null;
        }
    }
}
