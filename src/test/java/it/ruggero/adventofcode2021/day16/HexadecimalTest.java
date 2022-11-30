package it.ruggero.adventofcode2021.day16;

import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.convert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HexadecimalTest {

    @Test
    void shouldConvert() {
        assertThat(convert('0')).isEqualTo("0000");
        assertThat(convert('1')).isEqualTo("0001");
        assertThat(convert('2')).isEqualTo("0010");
        assertThat(convert('3')).isEqualTo("0011");
        assertThat(convert('4')).isEqualTo("0100");
        assertThat(convert('5')).isEqualTo("0101");
        assertThat(convert('6')).isEqualTo("0110");
        assertThat(convert('E')).isEqualTo("1110");
        assertThat(convert('F')).isEqualTo("1111");
    }

    @Test
    void shouldThrowException(){
        var exception = assertThrows(RuntimeException.class,
                () -> convert('r'));

        assertThat(exception.getMessage(), is("wrong input"));

    }
}
