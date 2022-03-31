package it.ruggero.adventofcode2021.day14.entity;

import it.ruggero.adventofcode2021.day13.entity.Coordinate;
import it.ruggero.adventofcode2021.day13.entity.Dot;
import it.ruggero.adventofcode2021.day13.entity.FoldInstruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class InputData {

    private String polymerTemplate;

    private Map<String,String> rules = new LinkedHashMap<>();

    public InputData(String filePath) {
        try (Scanner input = new Scanner(new File(filePath))) {
            while ( input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0 ){
                    continue;
                }
                if (!line.contains("->") && polymerTemplate == null) {
                    this.polymerTemplate = line;
                } else {
                    String[] parts = line.split("->");
                    String value  = "" + parts[0].trim().charAt(0) + parts[1].trim().charAt(0);
                    rules.put(parts[0].trim(),value);
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public String getPolymerTemplate() {
        return polymerTemplate;
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public String applyRuleOnce(String inputString) {
        String output ="";
        for(int index = 0; index < inputString.length() - 1; index++ ) {
            String key = inputString.substring(index,index+2);
            String value = rules.get(key);
            if (value != null) {
                output += value;
            }
        }


        return output + inputString.charAt(inputString.length() - 1);
    }


    public String applyRuleNTimes(int n) {
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
            char ch = (char) i);
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



}
