package org.xflash.edd.checkers.results;

import org.xflash.edd.model.Pair;
import org.xflash.edd.model.Pill;

import java.util.Collection;

public class CollapsedPillCheckResult implements CheckResult {
    private final Collection<Pair<Pill, Pill>> collapsingPills;

    public CollapsedPillCheckResult(Collection<Pair<Pill, Pill>> collapsingPills) {
        this.collapsingPills = collapsingPills;
    }
}
