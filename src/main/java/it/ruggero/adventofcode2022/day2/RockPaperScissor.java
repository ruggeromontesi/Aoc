package it.ruggero.adventofcode2022.day2;

public enum RockPaperScissor implements Comparable<RockPaperScissor>{
    ROCK(1), PAPER(2), SCISSOR(3);

    private int score;


    private RockPaperScissor(int score) {
        this.score =  score;
    }

    public int getScore() {
        return this.score;
    }


}
