package it.ruggero.adventofcode2022.day9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RopeBridgeTest {

    private static List<MotionInstruction> instructionListSample;

    @BeforeAll
    static void getInstruction() {
        instructionListSample = Day9ReadFile.readSample();
    }

    @Test
    void shouldDoSingleMove() {
        var rp = new RopeBridge();
        rp.moveSingleInstruction(instructionListSample.get(0));
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(4, 0, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(3, 0, "T"));
    }

    @Test
    void shouldDoTwoMovements() {
        var rp = new RopeBridge();
        var instructions = instructionListSample.stream().limit(2).collect(Collectors.toList());
        rp.moveMultipleInstructions(instructions);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(4, 4, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(4, 3, "T"));

    }

    @Test
    void shouldDoThreeMovements() {
        var rp = new RopeBridge();
        var instructions = instructionListSample.stream().limit(3).collect(Collectors.toList());
        rp.moveMultipleInstructions(instructions);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(1, 4, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2, 4, "T"));
    }

    @Test
    void shouldDoOnlyThirdStep() {
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(4, 4, "H"));
        rp.setTail(new RopeBridge.Point(4, 3, "T"));
        var m = instructionListSample.get(2);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(1, 4, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2, 4, "T"));

    }

    @Test
    void shouldDoOnlyFourthStep() {
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(1, 4, "H"));
        rp.setTail(new RopeBridge.Point(2, 4, "T"));
        var m = instructionListSample.get(3);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(1, 3, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2, 4, "T"));
    }

    @Test
    void shouldDoOnlyFifthStep() {
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(1, 3, "H"));
        rp.setTail(new RopeBridge.Point(2, 4, "T"));
        var m = instructionListSample.get(4);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(5, 3, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(4, 3, "T"));
    }

    @Test
    void shouldDoOnlySixthStep() {
        //D 1
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(5, 3, "H"));
        rp.setTail(new RopeBridge.Point(4, 3, "T"));
        var m = instructionListSample.get(5);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(5, 2, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(4, 3, "T"));
    }


    @Test
    void shouldDoOnlySeventhStep() {
        //L 5
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(5, 2, "H"));
        rp.setTail(new RopeBridge.Point(4, 3, "T"));
        var m = instructionListSample.get(6);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(0, 2, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(1, 2, "T"));
    }

    @Test
    void shouldDoAllMovements() {
        var rp = new RopeBridge(Day9ReadFile.readSample());
        rp.moveAllInstructions();
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(2, 2, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(1, 2, "T"));

        var list = List.of(
                new RopeBridge.Point(0, 0, "T"),
                new RopeBridge.Point(1, 0, "T"),
                new RopeBridge.Point(2, 0, "T"),
                new RopeBridge.Point(3, 0, "T"),

                new RopeBridge.Point(4, 1, "T"),

                new RopeBridge.Point(1, 2, "T"),
                new RopeBridge.Point(2, 2, "T"),
                new RopeBridge.Point(3, 2, "T"),
                new RopeBridge.Point(4, 2, "T"),

                new RopeBridge.Point(3, 3, "T"),
                new RopeBridge.Point(4, 3, "T"),
                new RopeBridge.Point(2, 4, "T"),
                new RopeBridge.Point(3, 4, "T")
        );
        assertThat(rp.getTailPointList()).containsAll(list);
    }


    @Test
    void shouldMoveDiagonallyWhenHeadMovesUp() {
        var rp = new RopeBridge(Day9ReadFile.read());
        rp.setHead(new RopeBridge.Point(2, 2, "H"));
        rp.setTail(new RopeBridge.Point(1, 1, "T"));
        MotionInstruction i = MotionInstruction
                .builder()
                .direction(Direction.U)
                .steps(1)
                .build();
        rp.moveSingleInstruction(i);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(2, 3, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2, 2, "T"));

    }

    @Test
    void shouldMoveDiagonallyWhenHeadMovesRight() {
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(2, 2, "H"));
        rp.setTail(new RopeBridge.Point(1, 1, "T"));
        MotionInstruction i = MotionInstruction
                .builder()
                .direction(Direction.R)
                .steps(1)
                .build();
        rp.moveSingleInstruction(i);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(3, 2, "H"));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2, 2, "T"));

    }

    @Test
    void shouldDoAllMovementsWithMainFile() {
        var rp = new RopeBridge(Day9ReadFile.read());
        rp.moveAllInstructions();
        assertThat(rp.getTailPointList()).hasSize(5902);

    }

    @Test
    void shouldInstantiateRopeBridgeWithMOreThanTwoKnots() {
        var rb = new RopeBridge(10);
        assertThat(rb.getKnots()).hasSize(10);
    }

    @Test
    void shouldThrowExceptionCallingPreviousOnHead() {
        var rb = new RopeBridge(10);
        var head = rb.getHead();
        assertThrows(RuntimeException.class, () -> rb.getPrevious(head));

    }

    @Test
    void ShouldReturnKnotPreviousToKnotWithIdOne(){
        var rb = new RopeBridge(10);
        var knot = rb.getKnotById("1");
        var previous = rb.getPrevious(knot);
        assertThat(previous.getId()).isEqualTo("H");
    }


    @Test
    void shouldReturnKnotPreviousToKnotWithIdTwo() {
        var rb = new RopeBridge(10);
        var knot = rb.getKnotById("2");
        var previous = rb.getPrevious(knot);
        assertThat(previous.getId()).isEqualTo("1");
    }

    @Test
    void shouldReturnKnotPreviousToTail() {
        var rb = new RopeBridge(10);
        var tail = rb.getKnotById("T");
        var previous = rb.getPrevious(tail);
        assertThat(previous.getId()).isEqualTo("8");
    }


    @Test
    void shouldDoAllMovementsWithMainFileAndTenKnots() {
        var rp = new RopeBridge(Day9ReadFile.read(),10);
        rp.moveAllInstructions();
        assertThat(rp.getTailPointList()).hasSize(2445);

    }

}
