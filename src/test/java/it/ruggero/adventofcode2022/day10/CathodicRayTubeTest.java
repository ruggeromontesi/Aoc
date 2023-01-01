package it.ruggero.adventofcode2022.day10;

import it.ruggero.adventofcode2022.Day10.CathodeRayTube;
import it.ruggero.adventofcode2022.Day10.input.Day10ReadFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CathodicRayTubeTest {

    @Test
    void shouldRunPartOneSample() {
        var a = Day10ReadFile.readSample();
        var b  = new CathodeRayTube(Day10ReadFile.readSample());
        b.run();
        assertThat(b.getSumOfSignalStrength()).isEqualTo(13140);
    }

    @Test
    void shouldRunPartOne() {
        var b  = new CathodeRayTube(Day10ReadFile.read());
        b.run();
       assertThat(b.getSumOfSignalStrength()).isEqualTo(12540);

    }

    @Test
    void shouldRunPartTwoSample() {
        var b = new CathodeRayTube(Day10ReadFile.readSample());
        b.run();
        b.printEnhanced();

    }

    @Test
    void shouldRunPartTwo() {
        var b = new CathodeRayTube(Day10ReadFile.read());
        b.run();
        b.printEnhanced();
        //FECZEHLE
    }

}
