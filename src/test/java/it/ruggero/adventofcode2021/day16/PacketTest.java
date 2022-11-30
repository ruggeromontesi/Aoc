package it.ruggero.adventofcode2021.day16;

import it.ruggero.adventofcode2021.day16.common.Packet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacketTest {

    private static final String PACKET_1_HEX = "D2FE28";

    @Test
    void shouldBuildPacket() {
        var packet = new Packet(PACKET_1_HEX);
        int version = packet .getVersion();
        assertEquals(version, 6);
        assertEquals(packet.getTypeId(), 4);
        assertThat(packet.getBitGroupList()).hasSize(3);


    }
}
