package it.ruggero.adventofcode2022.Day11;

import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MonkeyIntTheMiddle {

    @Getter
    private List<Monkey> monkeys;

    public MonkeyIntTheMiddle(List<MonkeyDto> monkeysDto) {
        this.monkeys = monkeysDto.stream().map(Monkey::new).collect(Collectors.toList());
    }

    public boolean monkeyHandleAnItem(Monkey m) {
        if (m.getMonkeyDto().getWorryLevels().isEmpty()) {
            return false;
        }
        int worryLevel = m.getWorryLevel();
        int changedWorryLevel = m.changeWorryLevel(worryLevel);
        int decreasedWorryLevel = m.decreaseWorryLevel(changedWorryLevel);
        int selectedMonkeyId = m.selectMonkey(decreasedWorryLevel);
        Monkey selectedMonkey = monkeys.stream().filter(monkey -> monkey.getMonkeyDto().getId() == selectedMonkeyId)
                .findFirst().orElseThrow();
        selectedMonkey.getMonkeyDto().getWorryLevels().offer(decreasedWorryLevel);

        return true;
    }

    public  void runNRounds(int n) {
        IntStream.range(0, n).forEach( i-> this.runARound());
    }


    public void runARound() {
        monkeys.forEach(this::runATurnForAMonkey);
    }


    public void runATurnForAMonkey(Monkey m) {
        boolean itemHandled;

        do {
           itemHandled = monkeyHandleAnItem(m);
        } while(itemHandled);
    }

    public int getMonkeyBusinessLevel() {
       return monkeys.stream().sorted(Comparator.comparingInt(Monkey::getInspectedItems).reversed()).limit(2).mapToInt(Monkey::getInspectedItems).reduce(1, (a,b) -> a*b);
    }
}
