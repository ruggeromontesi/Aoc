package it.ruggero.adventofcode2021.day16.common;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.binaryToInt;
import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.hexToBin;

@With
@Data
@Builder
public class PacketFactory {

    protected String inputString_hex;
    protected String inputString_bin;

    protected String inputStringToBeProcessed;

    private List<Packet> packets = new ArrayList<>();


    public void run(String inputString) {

        inputString_hex = inputString;
        inputString_bin = hexToBin(inputString);
        inputStringToBeProcessed = inputString_bin;


        while(!inputStringToBeProcessed.isEmpty()) {
            processOnePacket();
        }



    }

    public void processBinaryString(String inputString) {
        inputString_bin = inputString;
        inputStringToBeProcessed = inputString_bin;
        while(!inputStringToBeProcessed.isEmpty()) {
            processOnePacket();
        }

    }


    private String processOnePacket() {

        int typeId = getPacketTypeId();


        switch(typeId) {
            case 4:
                packets.add(buildLiteralPacket());
                break;

            case 6:
                packets.add(buildOperatorPacket());
                break;
        }

        return inputStringToBeProcessed;



    }

    private LiteralPacket buildLiteralPacket() {
        LiteralPacket literalPacket = new LiteralPacket(inputString_bin);
        inputStringToBeProcessed = literalPacket.getRemainderString();
        return literalPacket;
    }


    private OperatorPacket buildOperatorPacket() {

        OperatorPacket operatorPacket = new OperatorPacket(inputString_bin);
        inputStringToBeProcessed = operatorPacket.getRemainderString();

        return operatorPacket;

    }


    private int getPacketTypeId() {
        return binaryToInt(inputStringToBeProcessed.substring(3,6));
    }



}
