package org.xflash.edd.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pair;
import org.xflash.edd.model.Pill;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GridHeaderMinSolutionChecker implements SolutionChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(GridHeaderMinSolutionChecker.class);
    private final Grid grid;

    public GridHeaderMinSolutionChecker(Grid grid) {
        this.grid = grid;
    }

    @Override
    public GridHeaderMinCheckResult check(GridSolution solution) {
        Collection<Pill> pills = solution.getPills();
        LOGGER.debug("Checking if {} have all its pills don't overlap grid hints", solution);

        Map<Pair<Pill.Orientation, Integer>, Pair<Integer, Integer>> badpills = new HashMap<>();

//        Browse rows
        for (int h = 0; h < grid.cells.length; h++) {
            int[] row = grid.cells[h];
            int hintRowSum = grid.rowsSums[h];
            int rowSum = 0;

            for (int c = 0; c < row.length; c++) {
                for (Pill pill : pills) {
                    if (PillUtils.isPillIn(pill, c, h)) {
                        rowSum += grid.cells[h][c];
                    }
                }
            }
            if (rowSum > hintRowSum) {
                badpills.put(new Pair<>(Pill.Orientation.H, h), new Pair<>(rowSum, hintRowSum));
            }
        }


        for (int c = 0; c < grid.cells[0].length; c++) {
            int hintColSum = grid.colsSums[c];
            int colSum = 0;
            for (int h = 0; h < grid.cells.length; h++) {
                for (Pill pill : pills) {
                    if (PillUtils.isPillIn(pill, c, h)) {
                        colSum += grid.cells[h][c];
                    }
                }
            }
            if (colSum > hintColSum) {
                badpills.put(new Pair<>(Pill.Orientation.V, c), new Pair<>(colSum, hintColSum));
            }
        }

        if (!badpills.isEmpty()) {
            return new GridHeaderMinCheckResult(badpills);
        }

        return null;
    }

}
