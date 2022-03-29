package it.ruggero.adventofcode2021.day12;

import it.ruggero.adventofcode2021.day12.entity.Cave;
import org.junit.jupiter.api.Test;

public class CaveTest {

    @Test
    public void testCreateNode() {
        Cave node = new Cave("A");
        System.out.println("node is big : " + node.getBig());

    }

    @Test
    public void testIsBig(){
        String testString = "a";

        boolean isBig = testString.equals(testString.toUpperCase());
        System.out.println("is big " + isBig);

    }

}
