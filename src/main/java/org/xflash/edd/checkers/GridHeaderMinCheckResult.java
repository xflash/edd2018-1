package org.xflash.edd.checkers;

import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.model.Pair;
import org.xflash.edd.model.Pill;

import java.util.Map;

/**
 * @author rcoqueugniot
 * @since 11.02.18
 */
public class GridHeaderMinCheckResult implements CheckResult {
    private final Map<Pair<Pill.Orientation, Integer>, Pair<Integer, Integer>> badpills;

    public GridHeaderMinCheckResult(Map<Pair<Pill.Orientation, Integer>, Pair<Integer, Integer>> badpills) {
        this.badpills = badpills;
    }

    public Map<Pair<Pill.Orientation, Integer>, Pair<Integer, Integer>> getBadpills() {
        return badpills;
    }
}
