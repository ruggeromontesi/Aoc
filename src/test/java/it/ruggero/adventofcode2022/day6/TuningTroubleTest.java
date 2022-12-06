package it.ruggero.adventofcode2022.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2022.util.ParseFileUtility.getLinesFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class TuningTroubleTest {

    private static final String EXAMPLE_1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
    private static final String EXAMPLE_2 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
    private static final String EXAMPLE_3 = "nppdvjthqldpwncqszvftbrmjlhg";
    private static final String EXAMPLE_4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
    private static final String EXAMPLE_5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day6\\Day6.txt";
    private static final int NUMBER_OF_CHARS_PART_1 = 4;
    private static final int NUMBER_OF_CHARS_PART_2 = 14;
    private static final int RESULT_PART_1 = 1896;
    private static final int RESULT_PART_2 = 3452;

    private TuningTrouble tuningTrouble;

    @BeforeEach
    void prepare() {
        tuningTrouble = new TuningTrouble();
    }

    @Test
    void shouldFindStartOfPacket() {
        int startingIndex = tuningTrouble.apply(EXAMPLE_1, NUMBER_OF_CHARS_PART_1);
        assertThat(startingIndex).isEqualTo(7);
        startingIndex = tuningTrouble.apply(EXAMPLE_2, NUMBER_OF_CHARS_PART_1);
        assertThat(startingIndex).isEqualTo(5);
        startingIndex = tuningTrouble.apply(EXAMPLE_3, NUMBER_OF_CHARS_PART_1);
        assertThat(startingIndex).isEqualTo(6);
        startingIndex = tuningTrouble.apply(EXAMPLE_4, NUMBER_OF_CHARS_PART_1);
        assertThat(startingIndex).isEqualTo(10);
        startingIndex = tuningTrouble.apply(EXAMPLE_5, NUMBER_OF_CHARS_PART_1);
        assertThat(startingIndex).isEqualTo(11);
    }

    @Test
    void shouldFindStartOfPacketActualFile() {
        int startingIndex = tuningTrouble.apply(getLinesFromFile(FILE_PATH).get(0), NUMBER_OF_CHARS_PART_1);
        assertThat(startingIndex).isEqualTo(RESULT_PART_1);
    }

    @Test
    void shouldFindStartOfPacketActualFilePartTwo() {
        var line = getLinesFromFile(FILE_PATH).get(0);
        int startingIndex = tuningTrouble.apply(line, NUMBER_OF_CHARS_PART_2);
        assertThat(startingIndex).isEqualTo(RESULT_PART_2);
    }
}
