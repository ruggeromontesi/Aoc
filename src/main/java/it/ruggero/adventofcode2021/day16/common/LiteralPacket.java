package it.ruggero.adventofcode2021.day16.common;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.binToHex;
import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.hexToInt;

@Data
@Builder
public class LiteralPacket extends  Packet {

    private int literalValue;

    private String hexValue;

    private List<BitGroup> bitGroupList = new ArrayList<>();

    public LiteralPacket() {
    }

    public LiteralPacket(String inputString) {
        super(inputString);
        buildPacketTypeFour();
    }


    private void buildPacketTypeFour() {

        boolean isThisTheLastPacket = false;

        StringBuilder payload = new StringBuilder();
        int i;
        int j;
        for(i = 6,j = 0; i < inputString_bin.length() && j > -1; i++, j++) {
            char ch = inputString_bin.charAt(i);
            if(j == 0 && ch == '0') {
                isThisTheLastPacket = true;
            }
            payload.append(ch);
            if(j == 4 ) {
                bitGroupList.add(new BitGroup(payload.toString()));
                if(!isThisTheLastPacket) {
                    payload = new StringBuilder();
                    j = -1;
                } else {
                    j = -2;
                    int bytes = i/8;
                    endPacketIndex = (bytes+1)*8;

                }

            }
        }

        hexValue  = bitGroupList.stream().map(BitGroup::toHexValue).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();

        literalValue = hexToInt(hexValue);

        paddingZeroes = inputString_bin.substring(i);
        remainderString  = inputString_bin.substring(endPacketIndex);

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
