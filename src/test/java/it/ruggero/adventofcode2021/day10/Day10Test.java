
package it.ruggero.adventofcode2021.day10;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day10Test {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day10\\testDay10.txt";
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day10\\day10.txt";

    @Test
    public  void testCreateNavigationsubsystem( ) {
        NavigationSubsystem navigationSubsystem = new NavigationSubsystem(FILE_PATH_TEST);
        Assertions.assertNotNull(navigationSubsystem);
        navigationSubsystem = new NavigationSubsystem(FILE_PATH_ACTUAL);
        Assertions.assertNotNull(navigationSubsystem);
    }


    @Test
    public void testCalculateSyntaxErrorScore() {
        NavigationSubsystem navigationSubsystem = new NavigationSubsystem(FILE_PATH_TEST);
        navigationSubsystem = new NavigationSubsystem(FILE_PATH_ACTUAL);
        navigationSubsystem.parseAllLines();
        navigationSubsystem.calculateTotalSyntaxErrorScore();
        System.out.println("TotalSyntaxErrorScore " + navigationSubsystem.getTotalSyntaxErrorScore());

    }


    @Test
    public  void testCalculateMiddleScore() {
        NavigationSubsystem navigationSubsystem = new NavigationSubsystem(FILE_PATH_TEST);
        navigationSubsystem = new NavigationSubsystem(FILE_PATH_ACTUAL);
        navigationSubsystem.parseAllLines();
        navigationSubsystem.calculateAllMissingCharacterScore();
        Assertions.assertEquals(3354640192L,navigationSubsystem.calculateAllMissingCharacterScore());

    }





}
