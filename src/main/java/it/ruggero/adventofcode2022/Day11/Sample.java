package it.ruggero.adventofcode2022.Day11;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Sample {

    @Getter
    private List<MonkeyDto> monkeys = new ArrayList<>();


    public Sample() {
        addMonkeys();
    }

    private void addMonkeys() {
        MonkeyDto m0 = MonkeyDto
                .builder()
                .id(0)
                .worryLevels(new ArrayDeque<>(List.of(79, 98)))
                .operation(old -> old * 19)
                .test(i -> i % 23 == 0)
                .monkeyIfTrue(2)
                .monkeyIfFalse(3)
                .build();

        MonkeyDto m1 = MonkeyDto
                .builder()
                .id(1)
                .worryLevels(new ArrayDeque<>(List.of(54, 65, 75, 74)))
                .operation(old -> old + 6)
                .test(i -> i % 19 == 0)
                .monkeyIfTrue(2)
                .monkeyIfFalse(0)
                .build();
        MonkeyDto m2 = MonkeyDto
                .builder()
                .id(2)
                .worryLevels(new ArrayDeque<>(List.of(79, 60, 97)))
                .operation(old -> old * old)
                .test(i -> i % 13 == 0)
                .monkeyIfTrue(1)
                .monkeyIfFalse(3)
                .build();

        MonkeyDto m3 = MonkeyDto
                .builder()
                .id(3)
                .worryLevels(new ArrayDeque<>(List.of(74)))
                .operation(old -> old + 3)
                .test(i -> i % 17 == 0)
                .monkeyIfTrue(0)
                .monkeyIfFalse(1)
                .build();
        monkeys.addAll(List.of(m0,m1,m2,m3));
    }
}
