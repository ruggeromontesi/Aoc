package it.ruggero.adventofcode2022.day9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RopeBridgeTest {

    private static List<MotionInstruction> instructionListSample;

    @BeforeAll
    static void getInstruction() {
        instructionListSample = Day9ReadFile.readSample();
    }

    @Test
    void shouldDoSingleMove(){
        var rp = new RopeBridge();
        rp.moveSingleInstruction(instructionListSample.get(0));
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(4,0));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(3,0));
    }

    @Test
    void shouldDoTwoMovements() {
        var rp = new RopeBridge();
        var instructions = instructionListSample.stream().limit(2).collect(Collectors.toList());
        rp.moveMultipleInstructions(instructions);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(4,4));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(4,3));

    }

    @Test
    void shouldDoThreeMovements() {
        var rp = new RopeBridge();
        var instructions = instructionListSample.stream().limit(3).collect(Collectors.toList());
        rp.moveMultipleInstructions(instructions);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(1,4));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2,4));
    }

    @Test
    void shouldDoOnlyThirdStep() {
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(4,4));
        rp.setTail(new RopeBridge.Point(4,3));
        var m =instructionListSample.get(2);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(1,4));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2,4));

    }

    @Test
    void shouldDoOnlyFourthStep(){
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(1,4));
        rp.setTail(new RopeBridge.Point(2,4));
        var m =instructionListSample.get(3);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(1,3));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2,4));
    }

    @Test
    void shouldDoOnlyFifthStep() {
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(1,3));
        rp.setTail(new RopeBridge.Point(2,4));
        var m =instructionListSample.get(4);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(5,3));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(4,3));
    }

    @Test
    void shouldDoOnlySixthStep() {
        //D 1
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(5,3));
        rp.setTail(new RopeBridge.Point(4,3));
        var m =instructionListSample.get(5);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(5,2));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(4,3));
    }


    @Test
    void shouldDoOnlySeventhStep() {
        //L 5
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(5,2));
        rp.setTail(new RopeBridge.Point(4,3));
        var m =instructionListSample.get(6);
        rp.moveSingleInstruction(m);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(0,2));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(1,2));
    }

    @Test
    void shouldDoAllMovements() {
        var rp = new RopeBridge(Day9ReadFile.readSample());
        rp.moveAllInstructions();
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(2,2));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(1,2));

        var list =List.of(
                new RopeBridge.Point(0,0),
                new RopeBridge.Point(1,0),
                new RopeBridge.Point(2,0),
                new RopeBridge.Point(3,0),

                new RopeBridge.Point(4,1),

                new RopeBridge.Point(1,2),
                new RopeBridge.Point(2,2),
                new RopeBridge.Point(3,2),
                new RopeBridge.Point(4,2),

                new RopeBridge.Point(3,3),
                new RopeBridge.Point(4,3),
                new RopeBridge.Point(2,4),
                new RopeBridge.Point(3,4)
        );
        assertThat(rp.getTailPointList()).containsAll(list);
    }


    @Test
    void shouldMoveDiagonally(){
        var rp = new RopeBridge(Day9ReadFile.read());
        rp.setHead(new RopeBridge.Point(2,2));
        rp.setTail(new RopeBridge.Point(1,1));
        MotionInstruction i = MotionInstruction
                .builder()
                .direction(Direction.U)
                .steps(1)
                .build();
        rp.moveSingleInstruction(i);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(2,3));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2,2));

    }

    @Test
    void shouldMoveDiagonallySecondCase(){
        var rp = new RopeBridge();
        rp.setHead(new RopeBridge.Point(2,2));
        rp.setTail(new RopeBridge.Point(1,1));
        MotionInstruction i = MotionInstruction
                .builder()
                .direction(Direction.R)
                .steps(1)
                .build();
        rp.moveSingleInstruction(i);
        assertThat(rp.getHead()).isEqualTo(new RopeBridge.Point(3,2));
        assertThat(rp.getTail()).isEqualTo(new RopeBridge.Point(2,2));

    }

    @Test
    void shouldDoAllMovementsWithMainFile() {
        var rp = new RopeBridge(Day9ReadFile.read());
        rp.moveAllInstructions();
        assertThat(rp.getTailPointList()).hasSize(5902);

    }

}
