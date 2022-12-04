package it.ruggero.adventofcode2021.day16.common;

import lombok.Data;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.binaryToInt;

@Data
public class OperatorPacket extends Packet{

    private int lengthtypeId = -1;

    private int totalLengthInBitsOfSubpackets;





    public OperatorPacket() {
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
        //PacketFactory
        //var a = PacketFactory.getPackets();
        System.out.println("www");


    }


    private void processLengthTypeIdOne() {
        //totalLengthInBitsOfSubpackets = binaryToInt(inputString_bin.substring(7,21));


    }


    private int getLengthTypeId() {
        return binaryToInt(inputString_bin.substring(6,7));
    }
}
