package it.ruggero.util.input;

public class FileToSetOfLinesSeparatedByEmptyLines<T> extends FileToStringList {
    public FileToSetOfLinesSeparatedByEmptyLines(FilePathResolver filePathResolver) {
        super(filePathResolver);
    }
}
