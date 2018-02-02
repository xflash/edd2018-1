package org.xflash.edd.checkers;

import org.xflash.edd.GridSolution;
import org.xflash.edd.Pair;
import org.xflash.edd.Pill;

import java.util.HashSet;
import java.util.Set;

public class CollapsingSolutionChecker implements SolutionChecker {
    @Override
    public ResultCheck check(GridSolution solution) {
        Set<Pair<Pill, Pill>> collapsing = new HashSet<>();
        for (Pill activepill : solution.getPills()) {
            for (Pill pill : solution.getPills()) {
                if (pill.equals(activepill)) continue;
                if (pillCollapse(activepill, pill)) {
                    collapsing.add(new Pair<>(activepill, pill));
                }
            }
        }
        if (!collapsing.isEmpty())
            return new CollapsedPill(collapsing);

        return null;
    }

    private boolean pillCollapse(Pill l, Pill r) {
        for (int i = 0; i < 3; i++) {
            int x1 = l.H() ? l.x + i : l.x;
            int y1 = l.V() ? l.y + i : l.y;
            for (int j = 0; j < 3; j++) {
                int xr = r.H() ? r.x + j : r.x;
                int yr = r.V() ? r.y + j : r.y;
                if (xr == x1 && y1 == yr) return true;
            }
        }
        return false;
    }

}
