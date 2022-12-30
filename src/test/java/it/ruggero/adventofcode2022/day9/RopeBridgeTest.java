package it.ruggero.adventofcode2022.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RopeBridgeTest {

    @Test
    void shouldDoAllMovements() {
        var rp = new RopeBridge(Day9ReadFile.readSample(),2);
        rp.moveAllInstructions();
        assertThat(rp.getHead()).isEqualTo(new Knot(2, 2, "H"));
        assertThat(rp.getTail()).isEqualTo(new Knot(1, 2, "T"));

        var list = List.of(
                new Knot(0, 0, "T"),
                new Knot(1, 0, "T"),
                new Knot(2, 0, "T"),
                new Knot(3, 0, "T"),

                new Knot(4, 1, "T"),

                new Knot(1, 2, "T"),
                new Knot(2, 2, "T"),
                new Knot(3, 2, "T"),
                new Knot(4, 2, "T"),

                new Knot(3, 3, "T"),
                new Knot(4, 3, "T"),
                new Knot(2, 4, "T"),
                new Knot(3, 4, "T")
        );
        assertThat(rp.getTailPointList()).containsAll(list);
    }

    @Test
    void shouldDoAllMovementsWithMainFile() {
        var rp = new RopeBridge(Day9ReadFile.read(),2);
        rp.moveAllInstructions();
        assertThat(rp.getTailPointList()).hasSize(5902);

    }


    @Test
    void shouldDoAllMovementsWithMainFileAndTenKnots() {
        var rp = new RopeBridge(Day9ReadFile.read(),10);
        rp.moveAllInstructions();
        assertThat(rp.getTailPointList()).hasSize(2445);

    }

}
