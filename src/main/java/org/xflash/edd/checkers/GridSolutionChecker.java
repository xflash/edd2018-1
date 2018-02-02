package org.xflash.edd.checkers;

import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;

import java.util.HashSet;
import java.util.Set;

public class GridSolutionChecker {

    private final Set<SolutionChecker> solutionCheckers;

    public GridSolutionChecker(Grid grid) {
        solutionCheckers = new HashSet<>();
        solutionCheckers.add(new CollapsingSolutionChecker());
        solutionCheckers.add(new ValueSolutionChecker(grid));
        solutionCheckers.add(new GridHeaderSolutionChecker(grid));
    }

    public CheckResult check(GridSolution solution) {
        for (SolutionChecker solutionChecker : solutionCheckers) {
            CheckResult checkResult = solutionChecker.check(solution);
            if (checkResult != null) return checkResult;
        }
        return null;
    }
}
