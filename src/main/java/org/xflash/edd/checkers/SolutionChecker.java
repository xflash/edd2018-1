package org.xflash.edd.checkers;

import org.xflash.edd.GridSolution;

public interface SolutionChecker {
    ResultCheck check(GridSolution solution);
}
