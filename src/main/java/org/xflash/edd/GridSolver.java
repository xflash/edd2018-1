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
            findAllPillsMatching(i);
        }

        return Collections.emptyList();
    }

    Set<Pill> findAllPillsMatching(int t) {

        Set<Pill> matchingSums = new HashSet<>();

        GridBrowser gb = new GridBrowser(grid);
        gb.forEachPill(p -> {
            if (t == gb.sumPill(p))
                matchingSums.add(p);
        });

        return matchingSums;
    }

}
