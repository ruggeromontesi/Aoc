package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Board {

    private final Map<Coordinate,BingoNumber> table = new TreeMap<>(Comparator.comparingInt(Coordinate::getIndex));

    private boolean winning;

    public Map<Coordinate, BingoNumber> getTable() {
        return table;
    }

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }


}
