package org.xflash.edd.checkers;

import org.xflash.edd.GridSolution;
import org.xflash.edd.Pair;
import org.xflash.edd.Pill;
import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.CollapsedPillCheckResult;

import java.util.HashSet;
import java.util.Set;

/**
 * Enforces two pills don't overlap
 */
public class CollapsingSolutionChecker implements SolutionChecker {
    @Override
    public CheckResult check(GridSolution solution) {
        Set<Pair<Pill, Pill>> collapsing = new HashSet<>();
        for (Pill activepill : solution.getPills()) {
            for (Pill pill : solution.getPills()) {
                if (pill.equals(activepill)) continue;
                if (PillUtils.pillCollapse(activepill, pill)) {
                    collapsing.add(new Pair<>(activepill, pill));
                }
            }
        }
        if (!collapsing.isEmpty())
            return new CollapsedPillCheckResult(collapsing);

        return null;
    }

}
