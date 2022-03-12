package it.ruggero.adventofcode2021.day10.entity;

import java.util.*;

public class Line {

    private static Map<Character,Integer> illegalCharactersTable = new HashMap<>();
    private static Map<Character,Integer> missingCharactersTable = new HashMap<>();
    static {
        illegalCharactersTable.put(')',3);
        illegalCharactersTable.put(']',57);
        illegalCharactersTable.put('}',1197);
        illegalCharactersTable.put('>',25137);

        missingCharactersTable.put(')',1);
        missingCharactersTable.put(']',2);
        missingCharactersTable.put('}',3);
        missingCharactersTable.put('>',4);
    }

    private final static String openingTokens = "([{<";

    private final static String closingTokens = ")]}>";

    private String line;

    private Deque<Character> stack = new ArrayDeque<>();

    private boolean isCorrupted;

    private boolean isIncomplete;

    private int syntaxErrorScore;

    private String autocompletion = "";

    private long missingCharacterScore = 0;

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
        return syntaxErrorScore;
    }

    public String getAutocompletion() {
        return autocompletion;
    }

    public long getMissingCharacterScore() {
        return missingCharacterScore;
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
                        //System.out.println("Expected " + closingTokens.charAt(indexOfTheTokenOnStack) +", but found " + currentToken +" instead.");
                        syntaxErrorScore = illegalCharactersTable.get(currentToken);
                        isCorrupted = true;
                        return;

                    }
                }
            }
        }

        if( stack.size() != 0 ) {
            isIncomplete = true;
            while (stack.peek() != null) {
                char ch = stack.pop();
                int index = openingTokens.indexOf(ch);
                autocompletion += closingTokens.charAt(index);

            }
        }

    }

    public void calculateMissingCharacterScore() {
         long score = 0;

         for(int i =0; i< autocompletion.length(); i++) {
            score = 5*score + missingCharactersTable.get(autocompletion.charAt(i));
         }

         missingCharacterScore =  score;

    }
}
