package it.ruggero.adventofcode2022.Day11;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    @Getter
    private List<MonkeyDto> monkeys = new ArrayList<>();


    public TestCase() {
        addMonkeys();
    }

    private void addMonkeys() {
        MonkeyDto m0 = MonkeyDto
                .builder()
                .id(0)
                .worryLevels(new ArrayDeque<>(List.of(63, 84, 80, 83, 84, 53, 88, 72)))
                .operation(old -> old * 11)
                .test(i -> i % 13 == 0)
                .monkeyIfTrue(4)
                .monkeyIfFalse(7)
                .build();

        MonkeyDto m1 = MonkeyDto
                .builder()
                .id(1)
                .worryLevels(new ArrayDeque<>(List.of(67, 56, 92, 88, 84)))
                .operation(old -> old + 4)
                .test(i -> i % 11 == 0)
                .monkeyIfTrue(5)
                .monkeyIfFalse(3)
                .build();
        MonkeyDto m2 = MonkeyDto
                .builder()
                .id(2)
                .worryLevels(new ArrayDeque<>(List.of(52)))
                .operation(old -> old * old)
                .test(i -> i % 2 == 0)
                .monkeyIfTrue(3)
                .monkeyIfFalse(1)
                .build();

        MonkeyDto m3 = MonkeyDto
                .builder()
                .id(3)
                .worryLevels(new ArrayDeque<>(List.of(59, 53, 60, 92, 69, 72)))
                .operation(old -> old + 2)
                .test(i -> i % 5 == 0)
                .monkeyIfTrue(5)
                .monkeyIfFalse(6)
                .build();

        MonkeyDto m4 = MonkeyDto
                .builder()
                .id(4)
                .worryLevels(new ArrayDeque<>(List.of(61, 52, 55, 61)))
                .operation(old -> old + 3)
                .test(i -> i % 7 == 0)
                .monkeyIfTrue(7)
                .monkeyIfFalse(2)
                .build();

        MonkeyDto m5 = MonkeyDto
                .builder()
                .id(5)
                .worryLevels(new ArrayDeque<>(List.of(79, 53)))
                .operation(old -> old + 1)
                .test(i -> i % 3 == 0)
                .monkeyIfTrue(0)
                .monkeyIfFalse(6)
                .build();

        MonkeyDto m6 = MonkeyDto
                .builder()
                .id(6)
                .worryLevels(new ArrayDeque<>(List.of(59, 86, 67, 95, 92, 77, 91)))
                .operation(old -> old + 5)
                .test(i -> i % 19 == 0)
                .monkeyIfTrue(4)
                .monkeyIfFalse(0)
                .build();

        MonkeyDto m7 = MonkeyDto
                .builder()
                .id(7)
                .worryLevels(new ArrayDeque<>(List.of(58, 83, 89)))
                .operation(old -> old * 19)
                .test(i -> i % 17 == 0)
                .monkeyIfTrue(2)
                .monkeyIfFalse(1)
                .build();

        monkeys.addAll(List.of(m0,m1,m2,m3,m4,m5,m6,m7));
    }
}
