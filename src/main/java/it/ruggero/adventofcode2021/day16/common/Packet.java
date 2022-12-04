package it.ruggero.adventofcode2021.day16.common;

import lombok.Builder;
import lombok.Data;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.hexToBin;

@Data
@Builder
public class Packet {

    protected String inputString_hex;
    protected String inputString_bin;
    protected String packet_hexadecimal;
    protected String packet_bin;
    protected String remainderString;
    protected int endPacketIndex = 10;

    private int version;
    private int typeId;



    protected String paddingZeroes;


    public Packet() {
    }

    public Packet(String inputString) {
        this.inputString_hex = inputString;
        this.inputString_bin = hexToBin(inputString);


    }





}
