package org.xflash.edd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pill;
import org.xflash.edd.reader.GridBuilder;
import org.xflash.edd.solver.OldGridSolver;

import java.util.Collection;

public class OldGridSolverTest {

    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = GridBuilder
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
    }

    @Test
    public void checkBasic() throws Exception {
        OldGridSolver gridSolver = new OldGridSolver();
        Collection<GridSolution> gridSolutions = gridSolver.solve(grid, grid.w);
        Assert.assertEquals(1, gridSolutions.size());
        for (GridSolution gridSolution : gridSolutions) {
            Assert.assertArrayEquals(gridSolution.getPills(),
                    new Pill[]{
                            Pill.hpill(0, 2),
                            Pill.hpill(0, 2),
                            Pill.vpill(4, 1),
                            Pill.vpill(5, 0),
                            Pill.hpill(0, 5)
                    });
        }
    }


}