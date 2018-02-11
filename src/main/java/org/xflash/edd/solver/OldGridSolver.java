package org.xflash.edd.solver;

import org.xflash.edd.GridBrowser;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pill;

import java.util.*;

public class OldGridSolver implements GridSolver {

    public Collection<GridSolution> solve(Grid grid, int maxNb) {
        GridBrowser gridBrowser = new GridBrowser(grid);
        Map<Integer, Set<Pill>> map = compute4AllNumbers(maxNb, gridBrowser);

        List<GridSolution> sola = new ArrayList<>();

        for (Set<Pill> pills : map.values()) {
            for (Pill pill : pills) {
                List<Pill> sol = findSolStartingWith(pill, map, gridBrowser);
                if (!sol.isEmpty()) {
                    GridSolution gridSolution = new GridSolution();
                    gridSolution.addPill(pill);
                    sola.add(gridSolution);
                }
            }
        }

        return sola;
    }

    private List<Pill> findSolStartingWith(Pill rootPill, Map<Integer, Set<Pill>> map, GridBrowser gridBrowser) {
        List<Pill> sol = Collections.emptyList();

        Set<Pill> set = new HashSet<>();
        for (Set<Pill> pills : map.values()) {
            pills.stream()
                    .filter(p -> !gridBrowser.isOverlapping(rootPill, p))
                    .filter(gridBrowser::isPillCorrectSum)
                    .forEach(set::add);
        }

        return sol;
    }

    private Map<Integer, Set<Pill>> compute4AllNumbers(int maxNb, GridBrowser gridBrowser) {
        Map<Integer, Set<Pill>> map = new HashMap<>();
        for (int i = 1; i <= maxNb; i++) {
            map.put(i, findAllPillsMatching(i, gridBrowser));
        }
        return map;
    }

    private void filterOverlaps(Map<Integer, Set<Pill>> map, GridBrowser gridBrowser) {
        for (Map.Entry<Integer, Set<Pill>> pillsEntries : map.entrySet()) {
            Set<Pill> otherPills = new HashSet<Pill>();
            Set<Pill> pills = pillsEntries.getValue();
            for (Map.Entry<Integer, Set<Pill>> integerSetEntry : map.entrySet()) {
                if (!integerSetEntry.getKey().equals(pillsEntries.getKey())) {
                    otherPills.addAll(pills);
                }
            }
            pills.removeIf(p -> gridBrowser.isOverlapping(p, otherPills));
        }
    }


    public Set<Pill> findAllPillsMatching(int t, GridBrowser gridBrowser) {

        Set<Pill> matchingSums = new HashSet<>();

        gridBrowser.forEachPill(p -> {
            if (t == gridBrowser.sumPill(p))
                matchingSums.add(p);
        });

        return matchingSums;
    }

    Set<Pill> findAllPills(GridBrowser gridBrowser) {
        Set<Pill> matchingSums = new HashSet<>();
        gridBrowser.forEachPill(matchingSums::add);
        return matchingSums;
    }

}
