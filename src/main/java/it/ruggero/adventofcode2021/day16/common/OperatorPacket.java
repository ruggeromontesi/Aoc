package it.ruggero.adventofcode2021.day16.common;

import it.ruggero.adventofcode2021.day16.playground.PacketDecoder;
import lombok.Data;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.binaryToInt;

@Data
public class OperatorPacket extends Packet{

    private int lengthtypeId = -1;

    private int totalLengthInBitsOfSubpackets;
    private int quantityOfSubPackets;



    public OperatorPacket() {
    }

    public static OperatorPacket buildOperatorPacket(String inputString) {
        if (inputString == null || inputString.length() < 7) {
            return  null;
        } else {
            return new OperatorPacket(inputString);
        }
    }

    public OperatorPacket(String inputString) {
        super(inputString);
        lengthtypeId = getLengthTypeId();
        buildPacket();

    }


    private void buildPacket() {
        if (lengthtypeId == -1) {
            lengthtypeId = getLengthTypeId();
        }

        switch (lengthtypeId) {
            case 0 :
                processLengthTypeIdZero();
                break;
            case 1 :
                processLengthTypeIdOne();
                break;

            default: throw  new RuntimeException("Length type id not correctly initialized!!!!!");
        }
    }

    private void processLengthTypeIdZero() {
        totalLengthInBitsOfSubpackets = binaryToInt(inputString_bin.substring(7,22));
        String subpackets = inputString_bin.substring(22, 22 + totalLengthInBitsOfSubpackets);
        String remainder ;
        int previousSize ;
        do {
            previousSize = internalPacketList.size();
            remainder = PacketDecoder.runBin(subpackets,internalPacketList);
            subpackets = remainder;

        } while (internalPacketList.size() > previousSize);

        remainderString = remainder;
        System.out.println(remainder);
    }


    private void processLengthTypeIdOne() {
        quantityOfSubPackets = binaryToInt(inputString_bin.substring(7, 18));
        String subpackets = inputString_bin.substring(18);
        String remainder ;
        for(int i = 0; i < quantityOfSubPackets; i++ ) {
            subpackets = PacketDecoder.runBin(subpackets,internalPacketList);
        }
        inputString_bin = subpackets;
        remainderString = subpackets;
    }


    private  int getLengthTypeId() {
        if(inputString_bin.length() > 6) {
            return binaryToInt(inputString_bin.substring(6,7));
        } else {
            return  -1;
        }

    }
}
