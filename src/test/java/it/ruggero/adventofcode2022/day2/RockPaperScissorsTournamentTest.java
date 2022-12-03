package it.ruggero.adventofcode2022.day2;

import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2022.day2.RockPaperScissorsTournament.runTournamentPartOne;
import static it.ruggero.adventofcode2022.day2.RockPaperScissorsTournament.runTournamentPartTwo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RockPaperScissorsTournamentTest {

    private final static int PART_ONE_RESULT = 12156;
    private final static int PART_TWO_RESULT = 10835;

    @Test
    void shouldPlayTheWholeTournamentPartOne() {
        var totalScore = runTournamentPartOne();
        assertEquals(PART_ONE_RESULT, totalScore);
    }

    @Test
    void shouldPlayTheWholeTournamentPartTwo() {
        var totalScore = runTournamentPartTwo();
        assertEquals(PART_TWO_RESULT, totalScore);
    }
}
