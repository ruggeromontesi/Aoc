package it.ruggero.adventofcode2021.day16.playground;

import it.ruggero.adventofcode2021.day16.common.LiteralPacket;
import it.ruggero.adventofcode2021.day16.common.OperatorPacket;
import it.ruggero.adventofcode2021.day16.common.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.binaryToInt;

public class PacketDecoder {

    protected static String inputStringToBeProcessed;

    private static List<Packet> packets = new ArrayList<>();

    public static String runHex(String inputString, List<Packet> packetList) {
        packets(packetList);
        acceptStringHex(inputString);
        String remainder =  processOnePacket();
        return remainder ;
    }

    private static void packets(List<Packet> packetList) {
        packets = packetList;
    }

    public static String runBin(String inputString, List<Packet> packetList) {
        packets(packetList);
        acceptStringBin(inputString);
        String remainder =  processOneInternalPacket();
        return remainder ;
    }

    public static void acceptStringHex(String inputStringHex) {
        inputStringToBeProcessed = convertFromHexadecimalToBinaryString(inputStringHex);
    }

    public static void acceptStringBin(String inputStringBin) {
        inputStringToBeProcessed = inputStringBin;
    }

    private static String convertToBinary(Character c) {
        int intValue = Integer.parseInt(c + "", 16);
        var result = Integer.toBinaryString(intValue);
        StringBuilder padding = new StringBuilder();
        IntStream.range(0, 4 - result.length()).forEach( i-> padding.append("0"));
        return padding + result;
    }

    private static String convertFromHexadecimalToBinaryString(String inputString) {
        return IntStream.range(0,inputString.length()).mapToObj(inputString::charAt)
                .map(PacketDecoder::convertToBinary)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    private static int getPacketTypeId() {
        if(inputStringToBeProcessed.length() < 5 ) {
            return  -1;
        } else {
            return binaryToInt(inputStringToBeProcessed.substring(3,6));
        }
    }

    private static String processOnePacket() {
        int typeId = getPacketTypeId();
        switch(typeId) {
            case -1:
                System.out.println("corrupted packet");
            case 4:
                packets.add(buildLiteralPacket());
                break;

            default :
                packets.add(buildOperatorPacket());
                break;
        }
        return inputStringToBeProcessed;
    }

    private static String processOneInternalPacket() {
        int typeId = getPacketTypeId();
        switch(typeId) {
            case -1:
                break;
            case 4:
                packets.add(buildLiteralPacketInternal());
                break;

            default :
                packets.add(buildOperatorPacket());
                break;
        }
        return inputStringToBeProcessed;
    }

    private static Packet buildOperatorPacket() {
        OperatorPacket operatorPacket = OperatorPacket.buildOperatorPacket(inputStringToBeProcessed);
        if(operatorPacket != null) {
            inputStringToBeProcessed = operatorPacket.getRemainderString();
       }
        return operatorPacket;
     }

    private static Packet buildLiteralPacket() {
        LiteralPacket literalPacket = new LiteralPacket(inputStringToBeProcessed);
        inputStringToBeProcessed = literalPacket.getRemainderString();
        return literalPacket;
    }

    private static Packet buildLiteralPacketInternal() {
        LiteralPacket literalPacket = new LiteralPacket(inputStringToBeProcessed,true);
        inputStringToBeProcessed = literalPacket.getRemainderString();
        return literalPacket;
    }


    public static void main(String[] args) {
        String PACKET_1_HEX = "38006F45291200";
        PACKET_1_HEX = "EE00D40C823060";
        PACKET_1_HEX = "8A004A801A8002F478";
//        var p = new LiteralPacket(PACKET_1_HEX);
//        if(p.getLiteralValue() == 2021) {
//            System.out.println("ok!");
//        }

        List<Packet> packets =  new ArrayList<>();
        var x = runHex(PACKET_1_HEX,packets);
        Packet p = packets.get(0);
        int y = p.getVersionSum();



        System.out.println(x);
    }

}
