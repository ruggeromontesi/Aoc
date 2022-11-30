package it.ruggero.adventofcode2021.day16;

import org.junit.jupiter.api.Test;


import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HexadecimalTest {

    @Test
    void shouldConvert() {
        assertThat(convertChar('0')).isEqualTo("0000");
        assertThat(convertChar('1')).isEqualTo("0001");
        assertThat(convertChar('2')).isEqualTo("0010");
        assertThat(convertChar('3')).isEqualTo("0011");
        assertThat(convertChar('4')).isEqualTo("0100");
        assertThat(convertChar('5')).isEqualTo("0101");
        assertThat(convertChar('6')).isEqualTo("0110");
        assertThat(convertChar('E')).isEqualTo("1110");
        assertThat(convertChar('F')).isEqualTo("1111");
    }

    @Test
    void shouldThrowException(){
        var exception = assertThrows(RuntimeException.class,
                () -> convertChar('r'));

        assertThat(exception.getMessage(), is("wrong input"));

    }

    @Test
    void shouldConvertWholeString(){
        String input = "D2FE28";
        assertThat(hexToBin(input)).isEqualTo("110100101111111000101000");
    }

    @Test
    void shouldConvertBinaryToInt() {
        assertThat(binaryToInt("101")).isEqualTo(5);

    }
}
