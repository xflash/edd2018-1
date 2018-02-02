package org.xflash.edd.checkers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.GridSolution;
import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.CollapsedPillCheckResult;

import static org.xflash.edd.Pill.hpill;
import static org.xflash.edd.Pill.vpill;

public class CollapsingSolutionCheckerTest {

    private CollapsingSolutionChecker collapsingSolutionChecker;

    @Before
    public void setUp() throws Exception {
        collapsingSolutionChecker = new CollapsingSolutionChecker();
    }

    @Test
    public void checkCt() throws Exception {
        CheckResult rc = collapsingSolutionChecker.check(GridSolution.with(
                hpill(0, 2),
                vpill(3, 1),
                vpill(4, 0),
                hpill(0, 4)
        ));
        Assert.assertNull(rc);
    }

    @Test
    public void checkNoCollapse() throws Exception {
        CheckResult rc = collapsingSolutionChecker.check(GridSolution.with(
                hpill(0, 2),
                vpill(3, 1)
        ));
        Assert.assertNull(rc);
    }

    @Test
    public void checkCollapsedHPill() throws Exception {
        CheckResult rc = collapsingSolutionChecker.check(GridSolution.with(
                hpill(0, 0),
                hpill(1, 0)
        ));
        Assert.assertTrue(rc instanceof CollapsedPillCheckResult);

    }

    @Test
    public void checkCollapsedVPill() throws Exception {
        CheckResult rc = collapsingSolutionChecker.check(GridSolution.with(
                hpill(0, 0),
                vpill(1, 0)
        ));
        Assert.assertTrue(rc instanceof CollapsedPillCheckResult);
    }

}