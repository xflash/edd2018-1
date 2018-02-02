package org.xflash.edd;

import org.xflash.edd.checkers.GridHeaderSolutionChecker;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pair;
import org.xflash.edd.model.Pill;
import org.xflash.edd.solver.GridSolver;

import java.util.*;

public class NewGridSolver implements GridSolver {

    @Override
    public Collection<GridSolution> solve(Grid grid, int maxNb) {

        GridBrowser gridBrowser = new GridBrowser(grid);
        Set<Pill> pills = new HashSet<>();

//        CollapsingSolutionChecker collapsingSolutionChecker = new CollapsingSolutionChecker();
        GridHeaderSolutionChecker gridHeaderSolutionChecker = new GridHeaderSolutionChecker(grid);

        for (int n = maxNb; n <= 0; n--) {
            gridBrowser.forEachValuedPill(n, pills::add);
        }
        Set<Pill> filteredHeadValuesPills = new HashSet<>(pills);

        Map<Pair<Pill.Orientation, Integer>, Set<Pill>> badPills = gridHeaderSolutionChecker.checkBadPills(pills);
        for (Set<Pill> pillSet : badPills.values()) {
            for (Pill pill : pillSet) {
                pills.remove(pill);
            }
        }

        return Collections.singletonList(new GridSolution(new ArrayList<>(pills)));
    }


}
