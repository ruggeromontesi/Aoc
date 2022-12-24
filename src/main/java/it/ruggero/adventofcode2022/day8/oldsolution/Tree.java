package it.ruggero.adventofcode2022.day8.oldsolution;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreetopTreeHouseOld.Tree)) {
            return false;
        }

        Tree node = (Tree) o;

        if (height != node.height) return false;
        if (IsVisible != node.IsVisible) return false;
        return coordinate.equals(node.coordinate);
    }

    @Override
    public int hashCode() {
        int result = height;
        result = 31 * result + coordinate.hashCode();
        result = 31 * result + (IsVisible ? 1 : 0);
        return result;
    }

    @Override
    public int compareTo(@NotNull Tree o) {
        return this.height < o.getHeight() ? -1 : this.height != o.getHeight() ? 1 : 0;
    }
}
