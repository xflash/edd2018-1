package org.xflash.edd.checkers;

import org.xflash.edd.Pill;

import java.util.Collection;

public class ValueMismatch implements ResultCheck {

    private final Collection<Pill> mismatchedPills;

    public ValueMismatch(Collection<Pill> mismatchedPills) {
        this.mismatchedPills = mismatchedPills;
    }

}
