package it.ruggero.adventofcode2022.day4;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PairSectionAssignment {
    private int startElfOne;
    private int stopElfOne;
    private int startElfTwo;
    private int stopElfTwo;
}
