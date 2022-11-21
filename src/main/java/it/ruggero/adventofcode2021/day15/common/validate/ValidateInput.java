package it.ruggero.adventofcode2021.day15.common.validate;

import java.util.List;
import java.util.stream.IntStream;

public class ValidateInput {

    public static void validate(List<String> lines) {
        validateLineLength(lines);
        validateLineContent(lines);
    }

    private static void validateLineLength(List<String> lines) {
        int length = lines.get(0).length();
        if(lines.stream().anyMatch(s -> s.length() != length)) {
            throw new RuntimeException("Not all Strings are having same lenth!");
        }
    }

    private static void validateLineContent(List<String> lines) {
        lines.forEach(s -> IntStream.range(0,s.length()).forEach(i -> {
            if(Integer.parseInt(s.substring(i, i+1)) < 0 ) {
                throw new RuntimeException("Values are supposed to be positive");
            }
        }));

    }

}
