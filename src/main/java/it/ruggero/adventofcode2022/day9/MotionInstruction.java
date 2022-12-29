package it.ruggero.adventofcode2022.day9;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Builder
@With
@Data
public class MotionInstruction {
    private Direction direction;
    private Integer steps;
}
