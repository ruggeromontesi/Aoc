package it.ruggero.adventofcode2021.day16;

import it.ruggero.adventofcode2021.day16.common.OperatorPacket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OperatorPacketTest {

    private static final String PACKET_1_HEX = "EE00D40C823060";
    private static final String PACKET_1_LEGTH_TYPE_0 = "38006F45291200";


    @Test
    void shouldBuildPacket() {

        var p = new OperatorPacket(PACKET_1_LEGTH_TYPE_0);
        //assertThat(p.getLiteralValue()).isEqualTo(2021);
        assertThat(p).isNotNull();




    }
}
