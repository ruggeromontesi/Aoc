package it.ruggero.adventofcode2021.day12.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Cave {
    private String label;

    private Set<Cave> nodes = new HashSet<>();

    private Boolean big;

    public Cave(String label) {
        this.label = label;
        big = label.equals(label.toUpperCase()) ? true : (label.equals(label.toLowerCase()) ? false : null);
    }

    public String getLabel() {
        return label;
    }

    public Set<Cave> getNodes() {
        return nodes;
    }

    public Boolean getBig() {
        return big;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cave node = (Cave) o;

        return label.equals(node.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        String nodesToString = "";
        for(Cave cave : nodes) {
            nodesToString += "[" + cave.getLabel() +"] ";

        }

        return new StringJoiner(", ", Cave.class.getSimpleName() + "[", "]")
                .add("label='" + label + "'")
                .add("nodes=" + nodesToString )
                .add("big=" + big)
                .toString();
    }
}
