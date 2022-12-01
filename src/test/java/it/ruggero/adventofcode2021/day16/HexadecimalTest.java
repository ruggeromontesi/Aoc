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
        assertThat(hexToBinary('0')).isEqualTo("0000");
        assertThat(hexToBinary('1')).isEqualTo("0001");
        assertThat(hexToBinary('2')).isEqualTo("0010");
        assertThat(hexToBinary('3')).isEqualTo("0011");
        assertThat(hexToBinary('4')).isEqualTo("0100");
        assertThat(hexToBinary('5')).isEqualTo("0101");
        assertThat(hexToBinary('6')).isEqualTo("0110");
        assertThat(hexToBinary('E')).isEqualTo("1110");
        assertThat(hexToBinary('F')).isEqualTo("1111");
    }

    @Test
    void shouldThrowException(){
        var exception = assertThrows(RuntimeException.class,
                () -> hexToBinary('r'));

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


    @Test
    void shouldConvertHexToInt() {
        var a = hexToInt("7E5");
        assertThat(hexToInt("7E5")).isEqualTo(2021);

    }
}
