package it.ruggero.adventofcode2021.day14.entity;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class InputDataPart2Test {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day14\\testDay14.txt";

    @Test
    public void testInputDataCreation() {
        InputDataPart2 inputData = new InputDataPart2(FILE_PATH_TEST);
        //inputData.getRules().entrySet().forEach(System.out::println);
        System.out.println(inputData.applyRuleOnce(inputData.getPolymerTemplate()));
        System.out.println(inputData.getCountMap());
    }


    @Test
    public void testApplyRule() {
        InputDataPart2 inputData = new InputDataPart2(FILE_PATH_TEST);

        //String output = inputData.applyRuleOnce(inputData.getPolymerTemplate());
        //System.out.println(output);
        System.out.println(inputData.getCountMap());
        inputData.applyRuleNTimes(20);
        //System.out.println(inputData.applyRuleNTimes(10));
        System.out.println(inputData.getCountMap());

    }

    @Test
    public void testMethod(){
        InputDataPart2 inputData = new InputDataPart2(FILE_PATH_TEST);
        //inputData.method("aaaaaaaaaaaaaaac");
        IntStream.range(1,8).map(i -> inputData.applyRuleNTimes(i).length()).forEach(System.out::println);


    }
}
