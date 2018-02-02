package org.xflash.edd.checkers;

import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.model.GridSolution;

public interface SolutionChecker {
    CheckResult check(GridSolution solution);
}
