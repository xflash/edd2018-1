package org.xflash.edd.checkers;

import org.xflash.edd.GridSolution;
import org.xflash.edd.checkers.results.CheckResult;

public interface SolutionChecker {
    CheckResult check(GridSolution solution);
}
