package it.ruggero.adventofcode2021.day16;

import it.ruggero.adventofcode2021.day16.common.LiteralPacket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.Test;

//import static org.assertj.core.api.Assertions.assertThat;

public class LiteralPacketTest {

    private static final String PACKET_1_HEX = "D2FE28";


    @Test
    void shouldBuildPacket() {

        var p = new LiteralPacket(PACKET_1_HEX);
        assertThat(p.getLiteralValue()).isEqualTo(2021);




    }
}
