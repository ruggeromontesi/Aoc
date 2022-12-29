package it.ruggero.util.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileToStringList {

    private FilePathResolver filePathResolver;
    
    private String filePath;
    
    private final String SEPARATOR_NEW_LINE = "\\r\\n";

    private final String SEPARATOR_BLANK_LINE = "\\r\\n\\r\\n";
    
    private String separator = SEPARATOR_NEW_LINE;
    
    public FileToStringList(String filePath) {
        this.filePath = filePath;
    }

    public FileToStringList(FilePathResolver filePathResolver) {
        this.filePathResolver = filePathResolver;
    }

    private List<String> readFile() {
        Path source = Paths.get(filePath);
        try {
            String inputText = new String(Files.readAllBytes(source));
            Pattern pattern = Pattern.compile(SEPARATOR_BLANK_LINE);
            return  List.of(pattern.split(inputText));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       }
       
       public List<String> parseFileAsSingleList() {
        return readFile();
       }
       
       public List<List<String>> parseAsListOfLists() {
           Path source = Paths.get(filePath);
           try {
               String inputText = new String(Files.readAllBytes(source));
               Pattern pattern = Pattern.compile(SEPARATOR_BLANK_LINE);
               var strings = List.of(pattern.split(inputText));
               var a =  strings.stream().map( s -> List.of(Pattern.compile(SEPARATOR_NEW_LINE).split(s))).collect(Collectors.toList());
               List<List<String>> output = new ArrayList<>();
               for(String s : strings) {
                   var singleList = List.of(Pattern.compile(SEPARATOR_NEW_LINE).split(s));
                   output.add(singleList);
               }
               return output;
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        
       }

    public FileToStringList readSample() {
        this.filePath = filePathResolver.getSampleInputFilePath();
        return this;
    }

    public FileToStringList read() {
        this.filePath = filePathResolver.getInputFilePath();
        return this;
    }
    
    public FileToStringList withBlankLineSeparator() {
        separator = SEPARATOR_BLANK_LINE;
        return  this;
    }


}
