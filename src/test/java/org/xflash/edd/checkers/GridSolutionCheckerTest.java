package org.xflash.edd.checkers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.FileUtils;
import org.xflash.edd.checkers.results.CheckResult;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.reader.GridReader;

import static org.xflash.edd.model.Pill.hpill;
import static org.xflash.edd.model.Pill.vpill;

public class GridSolutionCheckerTest {

    private Grid grid;
    private GridSolutionChecker gridSolutionChecker;

    @Before
    public void setUp() throws Exception {
        grid = GridReader.from(FileUtils.classpath("grid1.txt"));
        gridSolutionChecker = new GridSolutionChecker(grid);
    }

    @Test
    public void checkPillOK() throws Exception {
        CheckResult rc = gridSolutionChecker.check(GridSolution.with(
                hpill(0, 2),
                vpill(3, 1),
                vpill(4, 0),
                hpill(0, 4)
        ));
        Assert.assertNull(rc);
    }

}