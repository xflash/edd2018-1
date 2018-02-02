package org.xflash.edd.checkers;

import org.xflash.edd.model.Pill;

public class PillUtils {
    static boolean pillCollapse(Pill l, Pill r) {
        for (int i = 0; i < 3; i++) {
            int x1 = l.H() ? l.x + i : l.x;
            int y1 = l.V() ? l.y + i : l.y;
            if (isPillIn(r, x1, y1)) return true;
        }
        return false;
    }

    static boolean isPillIn(Pill r, int x1, int y1) {
        for (int j = 0; j < 3; j++) {
            int xr = r.H() ? r.x + j : r.x;
            int yr = r.V() ? r.y + j : r.y;
            if (xr == x1 && y1 == yr) return true;
        }
        return false;
    }
}
