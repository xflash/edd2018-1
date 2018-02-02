package org.xflash.edd.checkers;

import org.xflash.edd.model.Grid;
import org.xflash.edd.model.Pill;

/**
 */
public class GridPillUtils {

    public static int computePillValInGrid(Pill pill, Grid grid) {
        int v = 0;
        for (int h = 0; h < 3; h++) {
            if (pill.H())
                v += grid.cells[pill.y][pill.x + h];
            else
                v += grid.cells[pill.y + h][pill.x];
        }
        return v;
    }
}
