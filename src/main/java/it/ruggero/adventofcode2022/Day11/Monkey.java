package it.ruggero.adventofcode2022.Day11;

import lombok.Getter;

public class Monkey {

    @Getter
    private MonkeyDto monkeyDto;

    @Getter
    private int inspectedItems = 0;

    public Monkey(MonkeyDto monkeyDto) {
        this.monkeyDto = monkeyDto;
    }

    public int getWorryLevel() {
        var worryLevels = monkeyDto.getWorryLevels();
        if (!worryLevels.isEmpty()) {
            inspectedItems++;
            return worryLevels.poll();
        }
        throw new RuntimeException("worryLevels is empty!");
    }

    public int changeWorryLevel(int worryLevel) {
        return monkeyDto.getOperation().apply(worryLevel);
    }

    public int decreaseWorryLevel(int worryLevel) {
        return (int) worryLevel/3;
    }

    public int selectMonkey(int worryLevel) {
        if (monkeyDto.getTest().test(worryLevel)) {
            return monkeyDto.getMonkeyIfTrue();
        } else {
            return monkeyDto.getMonkeyIfFalse();
        }
    }


}
