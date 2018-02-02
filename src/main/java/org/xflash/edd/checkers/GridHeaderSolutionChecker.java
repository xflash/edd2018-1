package org.xflash.edd.checkers;

import org.xflash.edd.Grid;
import org.xflash.edd.GridSolution;
import org.xflash.edd.Pair;
import org.xflash.edd.Pill;
import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.GridHeaderCheckResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GridHeaderSolutionChecker implements SolutionChecker {
    private final Grid grid;

    public GridHeaderSolutionChecker(Grid grid) {
        this.grid = grid;
    }

    @Override
    public CheckResult check(GridSolution solution) {
        Map<Pair<Pill.Orientation, Integer>, Set<Pill>> badpills = new HashMap<>();

//        Browse rows
        for (int h = 0; h < grid.cells.length; h++) {
            int[] row = grid.cells[h];
            int hintRowSum = grid.rowsSums[h];
            int rowSum = 0;
            Set<Pill> rowPills = new HashSet<>();

            for (int c = 0; c < row.length; c++) {
                for (Pill pill : solution.getPills()) {
                    if (PillUtils.isPillIn(pill, c, h)) {
                        rowPills.add(pill);
                        rowSum += grid.cells[h][c];
                    }
                }
            }
            if (hintRowSum != rowSum)
                badpills.put(new Pair<>(Pill.Orientation.H, h), rowPills);
        }


        for (int c = 0; c < grid.cells[0].length; c++) {
            int hintColSum = grid.colsSums[c];
            int colSum = 0;
            Set<Pill> colPills = new HashSet<>();
            for (int h = 0; h < grid.cells.length; h++) {
                for (Pill pill : solution.getPills()) {
                    if (PillUtils.isPillIn(pill, c, h)) {
                        colPills.add(pill);
                        colSum += grid.cells[h][c];
                    }
                }
            }
            if (hintColSum != colSum)
                badpills.put(new Pair<>(Pill.Orientation.V, c), colPills);
        }

        if (!badpills.isEmpty())
            return new GridHeaderCheckResult(badpills);

        return null;
    }

}
