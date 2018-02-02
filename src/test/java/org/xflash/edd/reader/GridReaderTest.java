package org.xflash.edd.reader;

import org.junit.Assert;
import org.junit.Test;
import org.xflash.edd.FileUtils;
import org.xflash.edd.model.Grid;

public class GridReaderTest {


    public static final int[][] GRID = {
            {1, 2, 2, 1, 2},
            {1, 0, 2, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 2, 3, 2, 2},
            {2, 1, 1, 1, 2},
    };
    public static final int[] COLS_SUMS = {2, 1, 2, 2, 3};
    public static final int[] ROWS_SUMS = {2, 1, 1, 2, 4};


    @Test(expected = IllegalArgumentException.class)
    public void checkBadRead() throws Exception {
        GridReader.from(FileUtils.classpath("bad_grid1.txt"));
    }

    @Test
    public void checkRead() throws Exception {
        assertGrid1(GridReader.from(FileUtils.classpath("grid1.txt")));
    }

    private void assertGrid1(Grid grid1) {
        Assert.assertNotNull(grid1);
        Assert.assertArrayEquals(COLS_SUMS, grid1.colsSums);
        Assert.assertArrayEquals(ROWS_SUMS, grid1.rowsSums);
        Assert.assertArrayEquals(GRID, grid1.cells);
    }

    @Test
    public void checkReadWithSpace() throws Exception {
        assertGrid1(GridReader.from(FileUtils.classpath("grid1_with_spaces.txt")));
    }

    @Test
    public void checkReadWithComments() throws Exception {
        assertGrid1(GridReader.from(FileUtils.classpath("grid1_with_comments.txt")));
    }

}
