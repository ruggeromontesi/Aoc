package it.ruggero.adventofcode2021.day16.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static it.ruggero.adventofcode2021.day16.common.HexadecimalToBinary.binaryToInt;

@Data
public class Packet {

    protected String inputString_hex;
    protected String inputString_bin;
    protected String packet_hexadecimal;
    protected String packet_bin;
    protected String remainderString;
    protected int endPacketIndex = 10;

    private int version;
    private int typeId;

    private int versionSum = -1111111111 ;

    protected List<Packet> internalPacketList = new ArrayList<>();



    protected String paddingZeroes;


    public Packet() {
    }

    public Packet(String inputString) {
        this.inputString_hex = inputString;
        this.inputString_bin = inputString;
        setVersion();



    }


    private void setVersion() {
        if(inputString_bin.length() > 2) {
            version = binaryToInt(inputString_bin.substring(0,3));
            //versionSum = version;
        } else {
            version = -1;
        }

    }

    public int getVersionSum() {
        if (versionSum < 0 ) {

            if (internalPacketList.isEmpty()) {
                versionSum = version;
                return versionSum;
            }


        }

        internalPacketList.forEach(packet -> {
            versionSum = version + packet.getVersionSum();
        });
        return  versionSum;
    }





}
