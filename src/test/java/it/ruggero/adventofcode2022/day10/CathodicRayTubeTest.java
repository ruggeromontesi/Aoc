package it.ruggero.adventofcode2022.day10;

import it.ruggero.adventofcode2022.Day10.CathodeRayTube;
import it.ruggero.adventofcode2022.Day10.input.Day10ReadFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CathodicRayTubeTest {

    @Test
    void shouldDoSomething() {
        var a = Day10ReadFile.readSample();
        System.out.println("ddddddd");

        var b  = new CathodeRayTube(Day10ReadFile.readSample());
        b.run();

//       assertThat(b.getRegister()).isEqualTo(-1);
//       assertThat(b.getClockCycle()).isEqualTo(5);
        assertThat(b.getSumOfSignalStrenght()).isEqualTo(13140);

    }

    @Test
    void shouldRun() {


        var b  = new CathodeRayTube(Day10ReadFile.read());
        b.run();

       assertThat(b.getSumOfSignalStrenght()).isEqualTo(12540);

    }

}
