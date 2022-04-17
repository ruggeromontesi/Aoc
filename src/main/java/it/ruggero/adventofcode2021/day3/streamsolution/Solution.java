package it.ruggero.adventofcode2021.day3.streamsolution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    List<String> strings = new ArrayList<>();

    public Solution(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }
    }

    public String calculateMostFrequentBit(int index){
        return strings.stream().collect(
                Counter::new,
                (Counter counter,String s) -> {
                    if (s.charAt(index) == '0') {
                        counter.n0++;
                    }
                    if (s.charAt(index) == '1') {
                        counter.n1++;
                    }
                },
                (Counter c1, Counter c2) -> { }
        ).getMostFrequentBit();
    }

    public String calculateGammaRate() {
        return IntStream.range(0, 12).mapToObj(this::calculateMostFrequentBit).collect(Collectors.joining());
    }

    public int calculatePowerConsumption() {
        return binaryToInt(calculateGammaRate()) * ((int)Math.pow(2,12) - 1 - binaryToInt(calculateGammaRate())) ;
    }

    class Counter {
        int n0;
        int n1;
        String getMostFrequentBit () {
            return (n0 > n1) ? "0" : "1";
        }
    }

    int binaryToInt(String str) {
        List<Character>  chars = str.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        class Temp {   int num = 0; }
        return IntStream.range(0, chars.size()).boxed().collect(
                Temp::new,
                (Temp t, Integer index) -> {
                    if (chars.get(index) == '1') {
                        t.num += (int) Math.pow(2, chars.size() - 1 - index);
                    }
                },
                (a,  b) -> {  }
        ).num;
    }
}
