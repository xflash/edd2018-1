package org.xflash.edd;

import java.util.*;

public class GridSolver {
    private Grid grid;

    public GridSolver(Grid grid) {

        this.grid = grid;
    }

    public List<Pill> solve() {
        compute4AllNumbers();

        return Collections.emptyList();
    }

    private Map<Integer, Set<Pill>> compute4AllNumbers() {
        int maxNb = grid.w;
        Map<Integer, Set<Pill>> map = new HashMap<>();
        for (int i = 1; i <= maxNb; i++) {
            map.put(i, findAllPillsMatching(i));
        }

        filterOverlaps(map);

        return map;
    }

    private void filterOverlaps(Map<Integer, Set<Pill>> map) {
        GridBrowser gb = new GridBrowser(grid);
        for (Map.Entry<Integer, Set<Pill>> pillsEntries : map.entrySet()) {
            Set<Pill> otherPills = new HashSet<Pill>();
            Set<Pill> pills = pillsEntries.getValue();
            for (Map.Entry<Integer, Set<Pill>> integerSetEntry : map.entrySet()) {
                if (!integerSetEntry.getKey().equals(pillsEntries.getKey())) {
                    otherPills.addAll(pills);
                }
            }
            pills.removeIf(p -> gb.checkOverlaps(p, otherPills));
        }
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
