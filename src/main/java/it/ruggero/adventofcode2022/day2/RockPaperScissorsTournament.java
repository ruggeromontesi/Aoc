package it.ruggero.adventofcode2022.day2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

import static it.ruggero.adventofcode2022.day2.common.ParseFileUtility.getLines;
import static it.ruggero.adventofcode2022.day2.common.ParseFileUtility.readFile;

public class RockPaperScissorsTournament {

    private RockPaperScissorsTournament() {
    }

    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day2\\Day2.txt";

    private static final ToIntFunction<char[]> partOne = (char[] choices) ->{
        char opponentChoice  = choices[0];
        char yourChoice  = choices[1];
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
    };

    private static final ToIntFunction<char[]> partTwo = (char[] choices) ->{
        char opponentChoice  = choices[0];
        char roundResult  = choices[1];
        Map<Character, RockPaperScissor> opponentChoiceEncryption = Map.of(
                'A', RockPaperScissor.ROCK,
                'B', RockPaperScissor.PAPER,
                'C', RockPaperScissor.SCISSOR);

        Map<Character,Integer> roundResultMap = Map.of(
                'X', 1,
                'Y', 0,
                'Z', -1
        );

        RockPaperScissor opponents = opponentChoiceEncryption.get(opponentChoice);

        final RpsComparator comparator = new RpsComparator();

        var result = roundResultMap.get(roundResult);
        assert (opponents != null) && (result != null);

        RockPaperScissor yours = Arrays.stream(RockPaperScissor.values()).filter(rps -> comparator.compare(opponents, rps) == result).findFirst().orElseThrow();

        RoundScoreCalculator calculator = new RoundScoreCalculator();
        return calculator.applyAsInt(opponents, yours);
    };

    public static int playTournament(List<String> lines, ToIntFunction<char[]> strategy) {
        return lines.stream().map(RockPaperScissorsTournament::lineToChars)
                .mapToInt(strategy).sum();
    }

    private static char[] lineToChars(String line) {
        return new char[]{line.charAt(0), line.charAt(2)};
    }

    public static int runTournamentPartOne() {
        readFile(FILE_PATH);
        return playTournament(getLines(), partOne);
    }

    public static int runTournamentPartTwo() {
        readFile(FILE_PATH);
        return playTournament(getLines(), partTwo);
    }

}
