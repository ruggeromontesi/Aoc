package it.ruggero.adventofcode2022.Day11;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.ArrayDeque;
import java.util.function.Function;
import java.util.function.Predicate;

@Builder
@With
@Data
public class MonkeyDto {
    int id;
    ArrayDeque<Integer> worryLevels;
    Function<Integer,Integer> operation;
    Predicate<Integer> test;
    int monkeyIfTrue;
    int monkeyIfFalse;

}
