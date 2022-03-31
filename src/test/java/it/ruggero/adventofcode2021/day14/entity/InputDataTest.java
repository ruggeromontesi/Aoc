package it.ruggero.adventofcode2021.day14.entity;

import org.junit.jupiter.api.Test;

public class InputDataTest {
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day14\\testDay14.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day14\\Day14.txt";

    @Test
    public void testInputDataCreation() {
        InputData inputData = new InputData(FILE_PATH_TEST);
        //inputData.getRules().entrySet().forEach(System.out::println);
        System.out.println(inputData.applyRuleOnce(inputData.getPolymerTemplate()));
    }

    @Test
    public void testApplyRule() {
        InputData inputData = new InputData(FILE_PATH_TEST);

        System.out.println(inputData.applyRuleOnce(inputData.getPolymerTemplate()));
        System.out.println(inputData.applyRuleNTimes(2));
        System.out.println(inputData.applyRuleNTimes(3));
        System.out.println(inputData.applyRuleNTimes(4));
        System.out.println(inputData.applyRuleNTimes(10));
    }


    @Test
    public  void testStringToList() {
        InputData inputData = new InputData(FILE_PATH_TEST);
        String str = "ruggero";
        inputData.stringToList(str).forEach(System.out::println);

    }

    @Test
    public void testGetDifference() {
        InputData inputData = new InputData(FILE_PATH_ACTUAL);
        inputData.getDifference(inputData.applyRuleNTimes(40));




    }


}
