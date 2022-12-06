package it.ruggero.adventofcode2022.day6;

import lombok.Getter;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TuningTrouble implements BiFunction<String,Integer,Integer> {



    @Getter
    public static Function<String, Integer> findStartOfPacketMarker = s -> {
        for (int k = 3; k + 3 < s.length(); k++) {
            String movingWindow = s.substring(k - 3, k + 1);
            var result = IntStream.range(0, movingWindow.length())
                    .mapToObj(movingWindow::charAt).collect(Collectors.toSet());
            if (result.size() == 4) {
                return k + 1;
            }
        }

        return -1;
    };


    @Getter
    public static Function<String, Integer> findStartOfPacketMarkerPartTwo = s -> {
        for (int k = 13; k + 13 < s.length(); k++) {
            String movingWindow = s.substring(k - 13, k + 1);
            var result = IntStream.range(0, movingWindow.length())
                    .mapToObj(movingWindow::charAt).collect(Collectors.toSet());
            if (result.size() == 14) {
                return k + 1;
            }
        }

        return -1;
    };

    @Override
    public Integer apply(String s, Integer numberOfIdenticalCharacters) {
        for (int k = numberOfIdenticalCharacters - 1 ; k + numberOfIdenticalCharacters - 1 < s.length(); k++) {
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
