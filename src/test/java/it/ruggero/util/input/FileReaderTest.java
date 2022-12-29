package it.ruggero.util.input;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class FileReaderTest {

    @Test
    void shouldReadFile() {
        var lines = new FileToStringList(new FilePathResolver(8,2022)).read().parseFileAsSingleList();
        assertThat(lines).isNotEmpty();
    }
    
   @Test
    void shouldReadValuesSeparatedByBlankLines() {
        var lines = new FileToStringList(new FilePathResolver(1,2022)).readSample().parseAsListOfLists();
        assertThat(lines).isNotEmpty();
    }


    @Test
    void shouldExtractIntArray(){
        var matrix = new FileToInteger2DArray(new FilePathResolver(15,2021)).read();
        assertThat(matrix).isNotEmpty();
    }



}
