package it.ruggero.adventofcode2021.day16.playground;

import java.util.Formatter;

public class Prova {
    public static void main(String[] args) {

        int a = 0xA;
        int b = 0xD2FE28;
        int c = 15;
        System.out.println(" a vale " + a);
        System.out.println(" b vale " + b);
        Formatter formatter =  new Formatter();
        formatter.format("%x", c);
        System.out.format("%x", c);
        System.out.println(Integer.toBinaryString(0xD2FE28));
        var x = Integer.toBinaryString(0xD2FE28);
    }
}
