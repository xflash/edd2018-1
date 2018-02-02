package org.xflash.edd.checkers.results;

import org.xflash.edd.Pair;
import org.xflash.edd.Pill;

import java.util.Map;
import java.util.Set;

public class GridHeaderCheckResult implements CheckResult {
    private final Map<Pair<Pill.Orientation, Integer>, Set<Pill>> pills;

    public GridHeaderCheckResult(Map<Pair<Pill.Orientation, Integer>, Set<Pill>> pills) {
        this.pills = pills;
    }
}
