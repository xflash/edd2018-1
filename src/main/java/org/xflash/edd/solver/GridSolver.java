package org.xflash.edd.solver;

import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;

import java.util.Collection;

public interface GridSolver {
    Collection<GridSolution> solve(Grid grid, int maxNb);
}
