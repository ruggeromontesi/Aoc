package it.ruggero.util.input;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

public class FileReaderSpeedComparison {

    private final String FILEPATH = ".\\src\\main\\resources\\util\\test.txt";

    @Test
    void shouldReadFile() {

        Instant now = Instant.now();



        var lines = new FileToStringList(FILEPATH).read().parseFileAsSingleList();

        Instant later = Instant.now();
        Duration duration  = Duration.between(now,later);


        System.out.println("Old : " + duration.toMillis() );

    }
}
