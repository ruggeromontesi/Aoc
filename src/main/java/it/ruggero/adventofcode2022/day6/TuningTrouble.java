package it.ruggero.adventofcode2022.day6;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TuningTrouble implements BiFunction<String, Integer, Integer> {

    @Override
    public Integer apply(String s, Integer numberOfIdenticalCharacters) {
        for (int k = numberOfIdenticalCharacters - 1; k + numberOfIdenticalCharacters - 1 < s.length(); k++) {
            String movingWindow = s.substring(k - numberOfIdenticalCharacters + 1, k + 1);
            var result = IntStream.range(0, movingWindow.length())
                    .mapToObj(movingWindow::charAt).collect(Collectors.toSet());
            if (result.size() == numberOfIdenticalCharacters) {
                return k + 1;
            }
        }
        return -1;
    }
}
