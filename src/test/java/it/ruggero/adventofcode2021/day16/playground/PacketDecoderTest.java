package it.ruggero.adventofcode2021.day16.playground;

import it.ruggero.adventofcode2021.day16.common.Packet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.ruggero.adventofcode2021.day16.playground.PacketDecoder.runHex;
import static org.assertj.core.api.Assertions.assertThat;

public class PacketDecoderTest {

    @Test
    void shouldCalculateversionSum() {
        String PACKET_1_HEX = "8A004A801A8002F478";



        List<Packet> packets =  new ArrayList<>();
        var x = runHex(PACKET_1_HEX,packets);
        Packet p = packets.get(0);
        int y = p.getVersionSum();
        assertThat(p.getVersionSum()).isEqualTo(16);
    }


    @Test
    void shouldCalculateversionSum2() {
        String PACKET_1_HEX = "620080001611562C8802118E34";
        List<Packet> packets =  new ArrayList<>();
        var x = runHex(PACKET_1_HEX,packets);
        Packet p = packets.get(0);
        int y = packets.stream().mapToInt(Packet::getVersionSum).sum();
        assertThat(p.getVersionSum()).isEqualTo(16);
    }
}
