package org.xflash.edd.checkers;

import org.xflash.edd.Grid;
import org.xflash.edd.GridSolution;

import java.util.HashSet;
import java.util.Set;

public class GridSolutionChecker {

    private final Set<SolutionChecker> solutionCheckers;

    public GridSolutionChecker(Grid grid) {
        solutionCheckers = new HashSet<>();
        solutionCheckers.add(new CollapsingSolutionChecker());
        solutionCheckers.add(new ValueSolutionChecker(grid));
    }

    public ResultCheck check(GridSolution solution) {
        for (SolutionChecker solutionChecker : solutionCheckers) {
            ResultCheck resultCheck = solutionChecker.check(solution);
            if (resultCheck != null) return resultCheck;
        }
        return null;
    }
}
