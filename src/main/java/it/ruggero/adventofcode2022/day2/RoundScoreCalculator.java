package it.ruggero.adventofcode2022.day2;

import java.util.function.ToIntBiFunction;

public class RoundScoreCalculator implements ToIntBiFunction<RockPaperScissor, RockPaperScissor> {

    private final RpsComparator comparator = new RpsComparator();

    @Override
    public int applyAsInt(RockPaperScissor opponent, RockPaperScissor your) {
        var roundResult = comparator.compare(opponent, your);
        switch (roundResult) {
            case -1:
                return 6 + your.getScore();

            case 0:
                return 3 + your.getScore();

            case 1:
                return your.getScore();

            default:
                throw new RuntimeException("Rps comparator not working!!");
        }

    }
}
