package it.ruggero.adventofcode2021.day16.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.*;

@Data
public class Packet {

    private String packet_h;

    private String packet_bin;


    private int literalValue;

    private String hexValue;

    private short version;
    private short typeId;

    private List<BitGroup> bitGroupList = new ArrayList<>();

    private String paddingZeroes;

    public Packet(String packet) {
        this.packet_h = packet;
        this.packet_bin = hexToBin(packet_h);
        buildPacket();
    }

    private void buildPacket() {
        version = (short) binaryToInt(packet_bin.substring(0,3));
        typeId = (short) binaryToInt(packet_bin.substring(3,6));
        StringBuilder payload = new StringBuilder();
        for(int i = 6, j = 0; i < packet_bin.length(); i++, j++) {
            payload.append(packet_bin.charAt(i));
            if(j == 4 && i < packet_bin.length() - 1) {
                bitGroupList.add(new BitGroup(payload.toString()));
                payload = new StringBuilder();
                j = -1;
            } else if (i == packet_bin.length() - 1) {
                paddingZeroes = payload.toString();
            }
        }

      hexValue  = bitGroupList.stream().map(BitGroup::toHexValue).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();

        literalValue = hexToInt(hexValue);


    }

    @Data
    public static class BitGroup {
        private char prefix;
        private char[] payload = new char[4];

        BitGroup(String bitGropuBinaryStringValue) {
            prefix = bitGropuBinaryStringValue.charAt(0);
            payload = bitGropuBinaryStringValue.substring(1).toCharArray();

        }

        private String toBinaryValue() {
            StringBuilder out = new StringBuilder();
            for(char c : payload) {
                out.append(c);
            }
            return out.toString();
        }


        public int toIntValue() {
            return HexadecimalToBinary.binaryToInt( toBinaryValue());
        }

        public char toHexValue() {
            return binToHex(toBinaryValue());
        }



    }
}
