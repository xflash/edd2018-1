package org.xflash.edd.checkers;

import org.xflash.edd.Pair;
import org.xflash.edd.Pill;

import java.util.Collection;

public class CollapsedPill implements ResultCheck {
    private final Collection<Pair<Pill, Pill>> collapsingPills;

    public CollapsedPill(Collection<Pair<Pill, Pill>> collapsingPills) {
        this.collapsingPills = collapsingPills;
    }
}
