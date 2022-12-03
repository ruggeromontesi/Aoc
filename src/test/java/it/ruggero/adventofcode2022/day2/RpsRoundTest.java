package it.ruggero.adventofcode2022.day2;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RpsRoundTest {




    @Test
    void shouldReturnScore() {
        var result = RockPaperScissor.ROCK.getScore();
        assertEquals(1,RockPaperScissor.ROCK.getScore());
        assertEquals(2,RockPaperScissor.PAPER.getScore());
        assertEquals(3,RockPaperScissor.SCISSOR.getScore());

    }

    @Test
    void matchTest() {
        var comparator = new RpsComparator();
        int result = comparator.compare(RockPaperScissor.ROCK,RockPaperScissor.ROCK);
        assertEquals(0, result);
        result = comparator.compare(RockPaperScissor.ROCK,RockPaperScissor.PAPER);
        assertTrue(result < 0);
        result = comparator.compare(RockPaperScissor.ROCK,RockPaperScissor.SCISSOR);
        assertTrue(result > 0);


        result = comparator.compare(RockPaperScissor.PAPER,RockPaperScissor.ROCK);
        assertTrue(result > 0);
        result = comparator.compare(RockPaperScissor.PAPER,RockPaperScissor.PAPER);
        assertEquals(0,result);
        result = comparator.compare(RockPaperScissor.PAPER,RockPaperScissor.SCISSOR);
        assertTrue(result < 0);

        result = comparator.compare(RockPaperScissor.SCISSOR,RockPaperScissor.ROCK);
        assertTrue(result < 0);
        result = comparator.compare(RockPaperScissor.SCISSOR,RockPaperScissor.PAPER);
        assertTrue(result > 0);
        result = comparator.compare(RockPaperScissor.SCISSOR,RockPaperScissor.SCISSOR);
        assertEquals(0,result);
    }

    @Test
    void shouldCalculateRoundResult() {
        System.out.println("--");
        RoundScoreCalculator calculator =  new RoundScoreCalculator();
        var score = calculator.applyAsInt(RockPaperScissor.ROCK, RockPaperScissor.PAPER);
        assertEquals(8,score);
        assertEquals(1,calculator.applyAsInt(RockPaperScissor.PAPER, RockPaperScissor.ROCK));
        assertEquals(6,calculator.applyAsInt(RockPaperScissor.SCISSOR, RockPaperScissor.SCISSOR));

    }


}
