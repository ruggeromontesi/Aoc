/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day8;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author montesr
 */
public class DisplayMapping {

    private static Map<Integer, Set<Character>> digits =  new HashMap<>();

    static {
        Set<Character> zero = new TreeSet<>();
        zero.add('c');
        zero.add('a');
        zero.add('g');
        zero.add('e');
        zero.add('d');
        zero.add('b');
        digits.put(0,zero);

        /****************************/
        Set<Character> one = new TreeSet<>();
        one.add('a');
        one.add('b');
        digits.put(1,one);
        /****************************/
        Set<Character> two = new TreeSet<>();
        two.add('a');
        two.add('c');
        two.add('d');
        two.add('f');
        two.add('g');
        digits.put(2,two);
        /****************************/
        Set<Character> three = new TreeSet<>();
        three.add('a');
        three.add('b');
        three.add('c');
        three.add('d');
        three.add('f');
        digits.put(3,three);
        /****************************/
        Set<Character> four = new TreeSet<>();
        four.add('a');
        four.add('b');
        four.add('e');
        four.add('f');
        digits.put(4,four);
        /****************************/
        Set<Character> five = new TreeSet<>();
        five.add('b');
        five.add('c');
        five.add('d');
        five.add('e');
        five.add('f');
        digits.put(5,five);
        /****************************/
        Set<Character> six = new TreeSet<>();
        six.add('b');
        six.add('c');
        six.add('d');
        six.add('e');
        six.add('f');
        six.add('g');
        digits.put(6,six);
        /****************************/
        Set<Character> seven = new TreeSet<>();
        seven.add('a');
        seven.add('b');
        seven.add('d');
        digits.put(7,seven);
        /****************************/
        Set<Character> eight = new TreeSet<>();
        eight.add('a');
        eight.add('b');
        eight.add('c');
        eight.add('d');
        eight.add('e');
        eight.add('f');
        eight.add('g');
        eight.add('g');
        digits.put(8,eight);
        /****************************/
        Set<Character> nine = new TreeSet<>();
        nine.add('a');
        nine.add('b');
        nine.add('c');
        nine.add('d');
        nine.add('e');
        nine.add('f');
        digits.put(9,nine);




    }

    public static Map<Integer, Set<Character>> getDigits() {
        return digits;
    }
}
