package org.xflash.edd.checkers.results;

import org.xflash.edd.model.Pill;

import java.util.Collection;

public class ValueMismatchCheckResult implements CheckResult {

    private final Collection<Pill> mismatchedPills;

    public ValueMismatchCheckResult(Collection<Pill> mismatchedPills) {
        this.mismatchedPills = mismatchedPills;
    }

}
