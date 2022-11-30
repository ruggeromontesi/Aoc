package it.ruggero.adventofcode2021.day16.common;

import static java.lang.Math.pow;

public class HexadecimalToBinary {

    public static String convertChar(char inputChar) {

        switch (inputChar) {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'A':
                return "1010";
            case 'B':
                return "1011";
            case 'C':
                return "1100";
            case 'D':
                return "1101";
            case 'E':
                return "1110";
            case 'F':
                return "1111";

        }
        throw new RuntimeException("wrong input");
    }

    public static String hexToBin(String inputString) {
        StringBuilder output = new StringBuilder();
        for(char c : inputString.toCharArray()) {
            output.append(convertChar(c));
        }
        return output.toString();
    }

    public static int binaryToInt(String inputString) {
        int intValue= 0;
        for(int i = 0; i < inputString.length(); i++) {
            char c =  inputString.charAt(i);
            if (c == '1') {
                intValue += pow(2, inputString.length() - 1 - i);
            } else if (c != '0') {
                throw  new RuntimeException("the input contains values different from 0 and 1!");
            }
        }

        return intValue;
    }
}
