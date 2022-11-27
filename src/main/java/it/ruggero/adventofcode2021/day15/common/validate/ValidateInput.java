package it.ruggero.adventofcode2021.day15.common.validate;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;


public class ValidateInput {

    public static void validate(List<String> lines) {
        validateLineLength(lines);
        validateLineContent(lines);
    }

    private static void validateLineLength(List<String> lines) {
        int length = lines.get(0).length();
        if(lines.stream().anyMatch(s -> s.length() != length)) {
            throw new RuntimeException("Not all Strings are having same length!");
        }
    }

    private static void validateLineContent(List<String> lines) {
        lines.forEach(s -> IntStream.range(0,s.length()).forEach(i -> {
            if(Integer.parseInt(s.substring(i, i+1)) < 0 ) {
                throw new RuntimeException("Values are supposed to be positive");
            }
        }));

    }

    public static void validate(String[][] valuesAsStringArray) {
        validateLineLength(valuesAsStringArray);
        validateLineContent(valuesAsStringArray);
    }

    private static void validateLineLength(String[][] valuesAsStringArray) {

        notNull(valuesAsStringArray);
        final int lineLength = valuesAsStringArray[0].length;

        validate(valuesAsStringArray, stringArray-> stringArray.length == lineLength,
                "Not all Strings are having same length!" );
    }

    private static void notNull(String[][] valuesAsStringArray) {
        if (ArrayUtils.isEmpty(valuesAsStringArray)) {
            throw new RuntimeException("Array containing acquired strings is either null or empty");
        }
    }

    private static void validateLineContent(String[][] valuesAsStringArray) {
        notNull(valuesAsStringArray);
        validate(valuesAsStringArray, (String[] stringArray)-> {
            boolean start = true;
            for(String s : stringArray) {
                start &= Integer.parseInt(s) > 0;
            }
            return start;
        },
                "Values are supposed to be positive");

    }

    private static void validate(String[][] valuesAsStringArray, Predicate<String[]> test, String exception) {
        for(String[] line : valuesAsStringArray) {
            if(!test.test(line)) {
                throw new RuntimeException(exception);
            }
        }
    }

}
