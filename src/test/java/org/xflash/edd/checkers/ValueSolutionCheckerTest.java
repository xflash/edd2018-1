package org.xflash.edd.checkers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.FileUtils;
import org.xflash.edd.Grid;
import org.xflash.edd.GridReader;
import org.xflash.edd.GridSolution;

import static org.xflash.edd.Pill.hpill;
import static org.xflash.edd.Pill.vpill;

public class ValueSolutionCheckerTest {

    private Grid grid;
    private ValueSolutionChecker valueSolutionChecker;

    @Before
    public void setUp() throws Exception {
        grid = GridReader.from(FileUtils.classpath("grid1.txt"));
        valueSolutionChecker = new ValueSolutionChecker(grid);
    }

    @Test
    public void checkPillOK() throws Exception {
        ResultCheck rc = valueSolutionChecker.check(GridSolution.with(
                hpill(0, 2),
                vpill(3, 1)
        ));
        Assert.assertNull(rc);
    }

    @Test
    public void checkPillValue() throws Exception {
        ResultCheck rc = valueSolutionChecker.check(GridSolution.with(
                hpill(0, 0)
        ));
        Assert.assertTrue(rc instanceof ValueMismatch);
    }

}