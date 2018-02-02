package org.xflash.edd.checkers;

import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.ValueMismatchCheckResult;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pill;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Enforce pills value (given by a order) are ok with grid cells value
 */
public class ValueSolutionChecker implements SolutionChecker {
    private final Grid grid;

    public ValueSolutionChecker(Grid grid) {
        this.grid = grid;
    }

    @Override
    public CheckResult check(GridSolution solution) {
        Set<Pill> badpills = new LinkedHashSet<>();
        for (int i = 0; i < solution.getPills().size(); i++) {
            Pill pill = solution.getPills().get(i);
            int val = computePillValInGrid(pill);
            if (val != (i + 1)) badpills.add(pill);
        }
        if (!badpills.isEmpty())
            return new ValueMismatchCheckResult(badpills);

        return null;
    }

    private int computePillValInGrid(Pill pill) {
        return GridPillUtils.computePillValInGrid(pill, grid);
    }

}
