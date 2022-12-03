package it.ruggero.adventofcode2022.day2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static it.ruggero.adventofcode2022.day2.RockPaperScissorsTournament.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RockPaperScissorsTournamentTest {

    private static int PART_ONE_RESULT = 12156;

    @Test
    void shouldPlaySingleRoundFollowingStrategy() {
        var firstRound = playSingleRoundFollowingStrategy('A', 'Y');
        var secondRound = playSingleRoundFollowingStrategy('B', 'X');
        var thirdRound = playSingleRoundFollowingStrategy('C', 'Z');
        assertEquals(8, firstRound);
        assertEquals(1, secondRound);
        assertEquals(6, thirdRound);

    }

    @Test
    void shouldThrowAssertionError() {
        assertThrows(AssertionError.class,
                () -> playSingleRoundFollowingStrategy('A', 'A'));
    }

    @Test
    void shouldPlayTheWholeTournamentTestCase() {
        var lines = List.of("A Y", "B X", "C Z");

        var totalScore = playTournamentFollowingStrategy(lines);
        assertEquals(15, totalScore);

    }


    @Test
    void shouldPlayTheWholeTournament() {
        var totalScore = runTournament();
        assertEquals(PART_ONE_RESULT, totalScore);
    }

}
