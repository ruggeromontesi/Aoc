package it.ruggero.adventofcode2022.day2;

import java.util.Comparator;

public class RpsComparator implements Comparator<RockPaperScissor> {
    @Override
    public int compare(RockPaperScissor o1, RockPaperScissor o2) {

        if (o1 == o2) {
            return 0;
        }

        if (o1 == RockPaperScissor.ROCK && o2 == RockPaperScissor.SCISSOR ||
                o1 == RockPaperScissor.PAPER && o2 == RockPaperScissor.ROCK ||
                o1 == RockPaperScissor.SCISSOR && o2 == RockPaperScissor.PAPER
        ) {
            return 1;
        }
        return -1;
    }
}
