package org.xflash.edd.model;

public class GridSolution {
    private final Pill[] pills;

    public GridSolution(Pill[] pills) {
        this.pills = pills;
    }

    public static GridSolution with(Pill... pills) {
        return new GridSolution(pills);
    }

    public Pill[] getPills() {
        return pills;
    }
}
