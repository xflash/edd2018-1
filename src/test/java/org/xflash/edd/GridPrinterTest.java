package org.xflash.edd;

import org.junit.Before;
import org.junit.Test;

public class GridPrinterTest {

    public static final int[][] GRID = {
            {1, 2, 2, 1, 2},
            {1, 0, 2, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 2, 3, 2, 2},
            {2, 1, 1, 1, 2},
    };
    public static final int[] COLS_SUMS = {2, 1, 2, 2, 3};
    public static final int[] ROWS_SUMS = {2, 1, 1, 2, 4};
    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = GridBuilder
                .with(5)
                .colsSums(COLS_SUMS)
                .cells(GRID)
                .rowsSums(ROWS_SUMS)
                .get();
    }

    @Test
    public void check() throws Exception {
        new GridPrinter(grid)
                .print(System.out);
    }
}