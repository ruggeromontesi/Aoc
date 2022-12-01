package it.ruggero.adventofcode2021.day16.common;

import static java.lang.Math.pow;

public class HexadecimalToBinary {

    public static String hexToBinary(char inputChar) {

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

    public static char binToHex(String inputChar) {

        switch (inputChar) {
            case "0000":
                return '0';
            case "0001":
                return '1';
            case "0010":
                return '2';
            case "0011":
                return '3';
            case "0100":
                return '4';
            case "0101":
                return '5';
            case "0110":
                return '6';
            case "0111":
                return '7';
            case "1000":
                return '8';
            case "1001":
                return '9';
            case "1010":
                return 'A';
            case "1011":
                return 'B';
            case "1100":
                return 'C';
            case "1101":
                return 'D';
            case "1110":
                return 'E';
            case "1111":
                return 'F';


        }
        throw new RuntimeException("wrong input");
    }

    public static String hexToBin(String inputString) {
        StringBuilder output = new StringBuilder();
        for (char c : inputString.toCharArray()) {
            output.append(hexToBinary(c));
        }
        return output.toString();
    }

    public static int binaryToInt(String inputString) {
        int intValue = 0;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '1') {
                intValue += pow(2, inputString.length() - 1 - i);
            } else if (c != '0') {
                throw new RuntimeException("the input contains values different from 0 and 1!");
            }
        }

        return intValue;
    }


    public static int hexToInt(String inputString) {
        int intValue = 0;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            int digitValue = binaryToInt(hexToBinary(c));
            intValue += digitValue * pow(16, inputString.length() - 1 - i);
        }

        return intValue;
    }


}
