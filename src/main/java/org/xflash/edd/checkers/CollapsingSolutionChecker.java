package org.xflash.edd.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.CollapsedPillCheckResult;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pair;
import org.xflash.edd.model.Pill;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Enforces two pills don't overlap
 */
public class CollapsingSolutionChecker implements SolutionChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollapsingSolutionChecker.class);

    @Override
    public CheckResult check(GridSolution solution) {
        LOGGER.debug("Checking if {} have some collapsing pills", solution);

        Set<Pair<Pill, Pill>> collapsing = checkPills(solution.getPills());
        if (!collapsing.isEmpty())
            return new CollapsedPillCheckResult(collapsing);

        return null;
    }

    Set<Pair<Pill, Pill>> checkPills(List<Pill> pills) {
        Set<Pair<Pill, Pill>> collapsing = new HashSet<>();
        for (Pill activepill : pills) {
            for (Pill pill : pills) {
                if (pill.equals(activepill)) continue;
                if (PillUtils.pillCollapse(activepill, pill)) {
                    collapsing.add(new Pair<>(activepill, pill));
                }
            }
        }
        return collapsing;
    }

}
