package org.xflash.edd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GridSolution {
    private final List<Pill> pills;

    public GridSolution(GridSolution gridSolution) {
        this.pills = new ArrayList<>(gridSolution.pills);
    }

    public GridSolution() {
        this.pills = new ArrayList<>();
    }

    public static GridSolution with(Pill... pills) {
        GridSolution gridSolution = new GridSolution();
        for (Pill pill : pills) {
            gridSolution.addPill(pill);
        }
        return gridSolution;
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

    @Override
    public String toString() {
        return "GridSolution " + pills;
    }

    public void addPill(Pill pill) {
        this.pills.add(pill);
    }
}
