package org.xflash.edd.checkers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.FileUtils;
import org.xflash.edd.Grid;
import org.xflash.edd.GridReader;
import org.xflash.edd.GridSolution;
import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.checkers.results.GridHeaderCheckResult;

import static org.xflash.edd.Pill.hpill;
import static org.xflash.edd.Pill.vpill;

public class GridHeaderSolutionCheckerTest {


    private Grid grid;
    private GridHeaderSolutionChecker gridHeaderSolutionChecker;

    @Before
    public void setUp() throws Exception {
        grid = GridReader.from(FileUtils.classpath("grid1.txt"));
        gridHeaderSolutionChecker = new GridHeaderSolutionChecker(grid);
    }

    @Test
    public void checkPillOK() throws Exception {
        CheckResult rc = gridHeaderSolutionChecker.check(GridSolution.with(
                hpill(0, 2),
                vpill(3, 1),
                vpill(4, 0),
                hpill(0, 4)
        ));
        Assert.assertNull(rc);
    }

    @Test
    public void checkBadSums() throws Exception {
        CheckResult rc = gridHeaderSolutionChecker.check(GridSolution.with(
                hpill(0, 2)
        ));
        Assert.assertNotNull(rc);
        Assert.assertTrue(rc instanceof GridHeaderCheckResult);
    }

}