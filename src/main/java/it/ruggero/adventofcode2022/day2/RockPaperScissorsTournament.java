package it.ruggero.adventofcode2022.day2;

import java.util.List;
import java.util.Map;

import static it.ruggero.adventofcode2022.day2.common.ParseFileUtility.getLines;
import static it.ruggero.adventofcode2022.day2.common.ParseFileUtility.readFile;

public class RockPaperScissorsTournament {

    private RockPaperScissorsTournament() {
    }

    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day2\\Day2.txt";

    public static int playSingleRoundFollowingStrategy(char opponentChoice, char yourChoice) {
        Map<Character, RockPaperScissor> opponentChoiceEncryption = Map.of(
                'A', RockPaperScissor.ROCK,
                'B', RockPaperScissor.PAPER,
                'C', RockPaperScissor.SCISSOR);

        Map<Character, RockPaperScissor> yourChoiceEncryption = Map.of(
                'X', RockPaperScissor.ROCK,
                'Y', RockPaperScissor.PAPER,
                'Z', RockPaperScissor.SCISSOR);

        RockPaperScissor opponents = opponentChoiceEncryption.get(opponentChoice);
        RockPaperScissor yours = yourChoiceEncryption.get(yourChoice);

        assert (opponents != null) && (yours != null);
        RoundScoreCalculator calculator = new RoundScoreCalculator();
        return calculator.applyAsInt(opponents, yours);
    }

    public static int playSingleRoundFollowingStrategy(char[] choices) {
        return playSingleRoundFollowingStrategy(choices[0], choices[1]);
    }


    public static int playTournamentFollowingStrategy(List<String> lines) {
        return lines.stream().map(RockPaperScissorsTournament::lineToChars)
                .mapToInt(RockPaperScissorsTournament::playSingleRoundFollowingStrategy).sum();
    }

    private static char[] lineToChars(String line) {
        return new char[]{line.charAt(0), line.charAt(2)};
    }

    public static int runTournament() {
        readFile(FILE_PATH);
        return playTournamentFollowingStrategy(getLines());
    }
}
