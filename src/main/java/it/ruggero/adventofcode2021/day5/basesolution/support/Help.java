package it.ruggero.adventofcode2021.day5.basesolution.support;

public class Help {
    public static boolean isInteger(double value) {
        if( (value - value%1) == value) {
            //System.out.println("is integer!");
            return true;
        }else {
            //System.out.println("is NOT integer!");
            return false;
        }
    }
}
