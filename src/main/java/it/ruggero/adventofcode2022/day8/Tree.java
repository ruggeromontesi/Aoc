package it.ruggero.adventofcode2022.day8;

import it.ruggero.util.core.AocMap;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.jetbrains.annotations.NotNull;

@Data
@With
@Builder
public class Tree implements Comparable<Tree> {
    private int height;
    private AocMap.Coordinate coordinate;
    Boolean IsVisible;

    @Override
    public int compareTo(@NotNull Tree o) {
        return this.height < o.getHeight() ? -1 : this.height != o.getHeight() ? 1 : 0;
    }
}
