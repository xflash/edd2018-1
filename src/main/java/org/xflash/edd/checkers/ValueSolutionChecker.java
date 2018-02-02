package org.xflash.edd.checkers;

import org.xflash.edd.Grid;
import org.xflash.edd.GridSolution;
import org.xflash.edd.Pill;

import java.util.LinkedHashSet;
import java.util.Set;

public class ValueSolutionChecker implements SolutionChecker {
    private final Grid grid;

    public ValueSolutionChecker(Grid grid) {
        this.grid = grid;
    }

    @Override
    public ResultCheck check(GridSolution solution) {
        Set<Pill> badpills = new LinkedHashSet<>();
        for (int i = 0; i < solution.getPills().length; i++) {
            Pill pill = solution.getPills()[i];
            int val = computePillValInGrid(pill);
            if (val != (i + 1)) badpills.add(pill);
        }
        if (!badpills.isEmpty())
            return new ValueMismatch(badpills);

        return null;
    }

    private int computePillValInGrid(Pill pill) {
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
