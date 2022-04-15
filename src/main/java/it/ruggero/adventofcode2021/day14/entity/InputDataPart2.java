package it.ruggero.adventofcode2021.day14.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputDataPart2 {

    private String polymerTemplate;

    private Map<String,String> rules = new LinkedHashMap<>();

    private Map<Character,Long> countMap = new HashMap<>();

    public InputDataPart2(String filePath) {
        try (Scanner input = new Scanner(new File(filePath))) {
            while ( input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0 ){
                    continue;
                }
                if (!line.contains(" -> ") && polymerTemplate == null) {
                    this.polymerTemplate = line;
                } else {
                    String[] parts = line.split(" -> ");
                    String value  = "" + parts[1].trim().charAt(0);
                    rules.put(parts[0].trim(),value);
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        for(int i = 0; i < polymerTemplate.length(); i++) {
            if(countMap.get(polymerTemplate.charAt(i)) == null) {
                countMap.put(polymerTemplate.charAt(i),1L);
            } else {
                countMap.put(polymerTemplate.charAt(i),countMap.get(polymerTemplate.charAt(i))+1);
            }

        }

    }

    public String getPolymerTemplate() {
        return polymerTemplate;
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public Map<Character, Long> getCountMap() {
        return countMap;
    }

    public String applyRuleOnce(String inputString) {
        String output = "";

        for(int index = 0; index < inputString.length() - 1; index++ ) {
            String key = inputString.substring(index,index+2);

            String value = rules.get(key);
            if (countMap.get(value.charAt(0)) == null) {
                countMap.put(value.charAt(0),1L);
            } else {
                countMap.put(value.charAt(0),countMap.get(value.charAt(0))+1);
            }

            if (value != null) {
                output += key.charAt(0) + value ;
            }

        }


        return output + inputString.charAt(inputString.length()-1);
    }


    public String applyRuleNTimes(Integer n) {
        String output ="";
        String input = polymerTemplate;
        for(int i = 0; i < n; i++) {
            output = applyRuleOnce(input);
            input = output;

        }

        return  output;
    }

    public int getDifference(String str) {
        int outputInt = 0;

        List<String> output = new ArrayList<>();
        for(int i = 0; i < str.length(); i++) {
            output.add(str.substring(i,i+1));
        }
        Map<Character, List<String>> map = output.stream().collect(Collectors.groupingBy( s ->  s.charAt(0)));
        System.out.println(map);

        /*map.entrySet().stream().sorted((e1,e2) -> e1.getValue().size() - e2.getValue().size()).forEach( e -> System.out.println(e.getValue().size()));
        map.entrySet().stream().mapToInt( e -> e.getValue().size()).sorted().forEach(System.out::println);
        outputInt = map.entrySet().stream().mapToInt( e -> e.getValue().size()).max().getAsInt() - map.entrySet().stream().mapToInt( e -> e.getValue().size()).min().getAsInt();
        System.out.println("difference " + outputInt);*/
        Map<Character,Long> charCount;
        str.chars().forEach( i -> System.out.print((char) i));



        return  outputInt;
    }

    public long getDifferenceLong(String str) {
        long output = 0L;
        Map<Character,Long> map = new HashMap<>();
        for( int i = 65; i < 95; i++) {
            long count = 0L;
            for(int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == (char) i) {
                    count++;
                }

            }
            char ch = (char) i;
            map.put(ch,count);

        }


        System.out.println(map);

        return output;
    }

    public long getDifferenceLongV1(String str) {
        long output = 0L;
        Map<Character,Long> map = new HashMap<>();
        for( int i = 65; i < 95; i++) {
            long count = 0L;
            for(int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == (char) i) {
                    count++;
                }

            }
            char ch = (char) i;
            map.put(ch,count);

        }


        System.out.println(map);

        return output;
    }



    public List<String> stringToList(String str ) {
        List<String> output = new ArrayList<>();
        for(int i = 0; i < str.length(); i++) {
            output.add(str.substring(i,i+1));
        }


        return  output;
    }


    public Map<String,Long> count(String input) {
        return IntStream.range(0,input.length()-1).mapToObj(i -> input.substring(i,i+2))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }



    public void method(String str) {
        Map<Character,Long> charCount = str.chars().mapToObj(i -> Character.valueOf((char)i)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        //str.chars().forEach( i -> System.out.print((char) i));
        System.out.println(charCount);
        System.out.println(charCount.entrySet().stream().min((e1,e2) -> (int)(e1.getValue() - e2.getValue())).get());

        //Map<Character,Long> charCount2 = IntStream.range(0,str.length()-1)

    }



}
