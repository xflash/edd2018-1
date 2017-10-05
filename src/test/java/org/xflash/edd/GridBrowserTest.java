package org.xflash.edd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.xflash.edd.Pill.Orientation.H;
import static org.xflash.edd.Pill.Orientation.V;

public class GridBrowserTest {

    public static final int[][] GRID = {
            {1, 2, 2, 1, 2},
            {1, 0, 2, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 2, 3, 2, 2},
            {2, 1, 1, 1, 2},
    };
    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = GridBuilder
                .with(5)
                .colsSums(new int[]{2, 1, 2, 2, 3})
                .cells(GRID)
                .rowsSums(new int[]{2, 1, 1, 2, 4})
                .get();
    }

    @Test
    public void checkRowsBrowsing() throws Exception {
        final int[] i = {0};
        new GridBrowser(grid)
                .forEachRow((row, r) -> {
                    Assert.assertArrayEquals("Row " + r, GRID[i[0]], row);
                    i[0]++;
                });
        Assert.assertEquals(5, i[0]);
    }

    @Test
    public void checkColsBrowsing() throws Exception {
        final int[] i = {0};

        final int[][] EXPECTED_COLS = {
                {1, 1, 0, 0, 2},
                {2, 0, 0, 2, 1},
                {2, 2, 1, 3, 1},
                {1, 0, 0, 2, 1},
                {2, 1, 0, 2, 2},

        };
        new GridBrowser(grid)
                .forEachCol((cell, c) -> {
                    Assert.assertArrayEquals("Col " + c, EXPECTED_COLS[i[0]], cell);
                    i[0]++;
                });
        Assert.assertEquals(5, i[0]);

    }

    @Test
    public void checkPillsBrowsing() throws Exception {
        final int[] i = {0};

        final Pill[] EXPECTED_PILLS = {
                new Pill(H, 0, 0), new Pill(H, 1, 0), new Pill(H, 2, 0),
                new Pill(H, 0, 1), new Pill(H, 1, 1), new Pill(H, 2, 1),
                new Pill(H, 0, 2), new Pill(H, 1, 2), new Pill(H, 2, 2),
                new Pill(H, 0, 3), new Pill(H, 1, 3), new Pill(H, 2, 3),
                new Pill(H, 0, 4), new Pill(H, 1, 4), new Pill(H, 2, 4),

                new Pill(V, 0, 0), new Pill(V, 0, 1), new Pill(V, 0, 2),
                new Pill(V, 1, 0), new Pill(V, 1, 1), new Pill(V, 1, 2),
                new Pill(V, 2, 0), new Pill(V, 2, 1), new Pill(V, 2, 2),
                new Pill(V, 3, 0), new Pill(V, 3, 1), new Pill(V, 3, 2),
                new Pill(V, 4, 0), new Pill(V, 4, 1), new Pill(V, 4, 2),


        };
        new GridBrowser(grid)
                .forEachPill(p -> {
                    Assert.assertEquals("Pill " + i[0], EXPECTED_PILLS[i[0]], p);
                    i[0]++;
                });
        Assert.assertEquals(30, i[0]);

    }
}