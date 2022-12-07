package it.ruggero.adventofcode2022.day7;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;

@Builder
@Data
@With
public class Directory {
    private Directory parent;
    private String name;

    private List<Directory> subdirectories;
    private List<File> files;
    private Long directorySize;

    public void calculateSize() {
        if(directorySize < 0) {
            long filesSize = (files != null) ? files.stream().mapToLong(File::getSize).sum() : 0;
            long subdirectoriesSize = (subdirectories != null) ? subdirectories.stream().mapToLong(Directory::getDirectorySize).sum() : 0;
            this.directorySize = filesSize + subdirectoriesSize;
        }
    }

    public Long getDirectorySize() {
        if(directorySize < 0) {
            calculateSize();
        }
        return directorySize;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", directorySize=" + directorySize +
                '}';
    }
}
