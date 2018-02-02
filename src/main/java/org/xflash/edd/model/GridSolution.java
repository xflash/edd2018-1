package org.xflash.edd.model;

import java.util.Arrays;
import java.util.List;

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
}
