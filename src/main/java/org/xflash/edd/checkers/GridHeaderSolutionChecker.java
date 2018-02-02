package org.xflash.edd.checkers;

import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.GridHeaderCheckResult;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pair;
import org.xflash.edd.model.Pill;

import java.util.*;

public class GridHeaderSolutionChecker implements SolutionChecker {
    private final Grid grid;

    public GridHeaderSolutionChecker(Grid grid) {
        this.grid = grid;
    }

    @Override
    public CheckResult check(GridSolution solution) {
        Map<Pair<Pill.Orientation, Integer>, Set<Pill>> badpills = checkBadPills(solution.getPills());

        if (!badpills.isEmpty())
            return new GridHeaderCheckResult(badpills);

        return null;
    }

    public Map<Pair<Pill.Orientation, Integer>, Set<Pill>> checkBadPills(Collection<Pill> pills) {
        Map<Pair<Pill.Orientation, Integer>, Set<Pill>> badpills = new HashMap<>();

//        Browse rows
        for (int h = 0; h < grid.cells.length; h++) {
            int[] row = grid.cells[h];
            int hintRowSum = grid.rowsSums[h];
            int rowSum = 0;
            Set<Pill> rowPills = new HashSet<>();

            for (int c = 0; c < row.length; c++) {
                for (Pill pill : pills) {
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
                for (Pill pill : pills) {
                    if (PillUtils.isPillIn(pill, c, h)) {
                        colPills.add(pill);
                        colSum += grid.cells[h][c];
                    }
                }
            }
            if (hintColSum != colSum)
                badpills.put(new Pair<>(Pill.Orientation.V, c), colPills);
        }
        return badpills;
    }

}
