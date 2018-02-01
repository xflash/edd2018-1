package org.xflash.edd;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

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

    @Test
    public void checkRead() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("grid1.txt");
        Grid grid1 = GridReader.from(new File(resource.getFile()));
        Assert.assertNotNull(grid1);
        Assert.assertArrayEquals(COLS_SUMS, grid1.colsSums);
        Assert.assertArrayEquals(ROWS_SUMS, grid1.rowsSums);
        Assert.assertArrayEquals(GRID, grid1.cells);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkBadRead() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("bad_grid1.txt");
        Grid grid1 = GridReader.from(new File(resource.getFile()));
    }
}
