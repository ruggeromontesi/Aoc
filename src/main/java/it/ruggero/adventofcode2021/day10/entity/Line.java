package it.ruggero.adventofcode2021.day10.entity;

import java.util.*;

public class Line {

    private static Map<Character,Integer> illegalCharacterTable = new HashMap<>();
    static {
        illegalCharacterTable.put(')',3);
        illegalCharacterTable.put(']',57);
        illegalCharacterTable.put('}',1197);
        illegalCharacterTable.put('>',25137);
    }

    private final static String openingTokens = "([{<";

    private final static String closingTokens = ")]}>";

    private String line;

    private Deque<Character> stack = new ArrayDeque<>();

    private boolean isCorrupted;

    private boolean isIncomplete;

    private int sintaxErrorScore;

     public String getLine() {
        return line;
    }

    public Queue<Character> getStack() {
        return stack;
    }

    public boolean isCorrupted() {
        return isCorrupted;
    }

    public boolean isIncomplete() {
        return isIncomplete;
    }

    public Line(String line) {

        for (int i =0; i < line.length(); i++) {
            String ch = line.charAt(i) + "";
            if ( !(openingTokens + closingTokens).contains(ch) ) {
               throw new RuntimeException("wrong character in the input file " + ch);
            }

            if( !isThisCharAnOpeningToken(line.charAt(i)) && !isThisCharAClosingToken(line.charAt(i))) {
                throw new RuntimeException("wrong character in the input file " + ch);
            }
        }
        this.line = line;
    }

    private boolean isThisCharAnOpeningToken(char ch) {
        String str = ch +"";
        if (openingTokens.contains(str)) {
            return true;
        }

        return false;
    }

    private boolean isThisCharAClosingToken(char ch) {
        String str = ch +"";
        if (closingTokens.contains(str)) {
            return true;
        }

        return false;
    }

    public int getSintaxErrorScore() {
        return sintaxErrorScore;
    }

    public void parse() {
        for(int i = 0; i < line.length(); i++) {
            Character tokenAtTopOfStack = stack.peek();
            Character currentToken = line.charAt(i) ;
            if (tokenAtTopOfStack == null) {
                stack.push(currentToken);
                continue;
            }
            if(isThisCharAnOpeningToken(currentToken)){
                stack.push(currentToken);
            } else {
                if(isThisCharAClosingToken(currentToken)) {
                    int indexOfTheNewToken = closingTokens.indexOf(currentToken);
                    int indexOfTheTokenOnStack = openingTokens.indexOf(tokenAtTopOfStack);
                    if ( indexOfTheNewToken == indexOfTheTokenOnStack) {
                        stack.pop();
                    } else {
                        System.out.println("Expected " + closingTokens.charAt(indexOfTheTokenOnStack) +", but found " + currentToken +" instead.");
                        sintaxErrorScore = illegalCharacterTable.get(currentToken);
                        isCorrupted = true;
                        return;

                    }
                }
            }
        }

        if( stack.size() != 0 ) {
            isIncomplete = true;
        }

    }
}
