package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Board {

    private Map<Coordinate,BingoNumber> table = new TreeMap<>(Comparator.comparingInt(Coordinate::getIndex));

    public Map<Coordinate, BingoNumber> getTable() {
        return table;
    }
}
