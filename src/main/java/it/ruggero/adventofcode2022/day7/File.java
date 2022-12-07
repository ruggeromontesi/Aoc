package it.ruggero.adventofcode2022.day7;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Builder
@With
@Data
public class File {
    private String name;
    private Long size;
}
