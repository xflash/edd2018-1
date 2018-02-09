package org.xflash.edd.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GridSolution {
    private final List<Pill> pills;

    public GridSolution(List<Pill> pills) {
        this.pills = pills;
    }

    public static GridSolution with(Pill... pills) {
        return new GridSolution(Arrays.asList(pills));
    }

    public List<Pill> getPills() {
        return pills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridSolution that = (GridSolution) o;
        return Objects.equals(pills, that.pills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pills);
    }
}
