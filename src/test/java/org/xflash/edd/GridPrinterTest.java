package org.xflash.edd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Set;

import static org.xflash.edd.Pill.Orientation.H;

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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new GridPrinter(grid, new PrintStream(baos))
                .print();
        Assert.assertEquals(
                "  2 1 2 2 3\n" +
                        " +---------\n" +
                        "2|1 2 2 1 2\n" +
                        "1|1 0 2 0 1\n" +
                        "1|0 0 1 0 0\n" +
                        "2|0 2 3 2 2\n" +
                        "4|2 1 1 1 2\n",
                baos.toString());
    }

    @Test
    public void checkWithMap() throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        new GridPrinter(grid, new PrintStream(baos))
                .printWithPill(Arrays.asList(new Pill(H, 0, 0)));
        Assert.assertEquals(
                "  2 1 2 2 3\n" +
                " +---------\n" +
                "2|1 2 2 1 2\n" +
                "1|1 0 2 0 1\n" +
                "1|0 0 1 0 0\n" +
                "2|0 2 3 2 2\n" +
                "4|2 1 1 1 2\n",
                baos.toString());
    }
}
