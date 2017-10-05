package org.xflash.edd;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridSolver {
    private Grid grid;

    public GridSolver(Grid grid) {

        this.grid = grid;
    }

    public List<Pill> solve() {
        int maxNb = grid.w;
        for (int i = 1; i <= maxNb; i++) {
            findAllPossibilitiesFor(i);
        }

        return Collections.emptyList();
    }

    private Set<Pill> findAllPossibilitiesFor(int t) {
        HashSet<Pill> pills = new HashSet<Pill>();

        new GridBrowser(grid)
                .forEachPill(p -> {
                });


        return pills;
    }

}
