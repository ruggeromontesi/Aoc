package it.ruggero.adventofcode2022.day11;

import it.ruggero.adventofcode2022.Day11.MonkeyIntTheMiddle;
import it.ruggero.adventofcode2022.Day11.Sample;
import it.ruggero.adventofcode2022.Day11.TestCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MonkeyIntTheMiddleTest {

    @Test
    void shouldRunSample() {
        var monkeyList = new Sample().getMonkeys();
        var mim = new MonkeyIntTheMiddle(monkeyList);
        mim.runNRounds(20);
        assertThat(mim.getMonkeyBusinessLevel()).isEqualTo(10605);
    }

    @Test
    void shouldRun() {
        var monkeyList = new TestCase().getMonkeys();
        var mim = new MonkeyIntTheMiddle(monkeyList);
        mim.runNRounds(20);
        assertThat(mim.getMonkeyBusinessLevel()).isEqualTo(117640);
    }

}
