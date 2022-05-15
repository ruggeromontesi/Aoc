package it.ruggero.adventofcode2021.day4.streamsolution.entity;

import org.junit.jupiter.api.Test;

public class BingoSessionTest {

    String filePath = ".\\src\\main\\resources\\day4\\testBingo.txt";

    @Test
    public void testCreateBingoSession() {
        BingoSession bingoSession = new BingoSession(filePath);
        bingoSession.getDrawnNumbers().forEach(System.out::println);


    }
}
