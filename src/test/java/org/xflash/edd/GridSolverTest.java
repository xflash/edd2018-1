package org.xflash.edd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.xflash.edd.Pill.Orientation.H;
import static org.xflash.edd.Pill.Orientation.V;
import static org.xflash.edd.PillMatchers.isAPill;

public class GridSolverTest {

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
        GridSolver gridSolver = new GridSolver(grid);
        Collection<List<Pill>> pills = gridSolver.solve();
        Assert.assertThat(pills, hasItems(
                hasItems(isAPill(H, 0, 2),
                        isAPill(H, 0, 2),
                        isAPill(V, 4, 1),
                        isAPill(V, 5, 0),
                        isAPill(H, 0, 5))
        ));
    }


}