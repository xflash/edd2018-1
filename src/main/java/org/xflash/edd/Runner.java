package org.xflash.edd;

import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.reader.GridBuilder;
import org.xflash.edd.solver.GridSolver;

import java.util.Collection;

public class Runner {

    public static void main(String[] args) {
        Grid grid = GridBuilder
                .with(5)
                        .colsSums(new int[]{2, 1, 2, 2, 3})
                        .cells(new int[][]{
                                        {1, 2, 2, 1, 2},
                                        {1, 0, 2, 0, 1},
                                        {0, 0, 1, 0, 0},
                                        {0, 2, 3, 2, 2},
                                        {2, 1, 1, 1, 2},
                                })
                        .rowsSums(new int[]{2, 1, 1, 2, 4})
                        .get();

//        GridSolver gridSolver = new OldGridSolver();
        GridSolver gridSolver = new NewGridSolver();
        Collection<GridSolution> solutions = gridSolver.solve(grid, 4);


    }
}
