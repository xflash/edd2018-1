package org.xflash.edd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.Pill;
import org.xflash.edd.reader.GridBuilder;

import static org.xflash.edd.model.Pill.Orientation.H;
import static org.xflash.edd.model.Pill.Orientation.V;

public class GridBrowserTest {

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

    @Test
    public void checkSumPills() throws Exception {
        GridBrowser gridBrowser = new GridBrowser(grid);

        int[] EXPECTED_SUMS = {
                5, 5, 5, 3, 2, 3, 1, 1, 1, 5, 7, 7, 4, 3, 4,
                2, 1, 2, 2, 2, 3, 5, 6, 5, 1, 2, 3, 3, 3, 4,
        };
        final int[] i = {0};
        gridBrowser.forEachPill(p -> {
            int sum = gridBrowser.sumPill(p);
            Assert.assertEquals("Pill sum " + p, EXPECTED_SUMS[i[0]++], sum);
        });

//        Assert.assertEquals("Pill H1",5, gridBrowser.sumPill(new Pill(H, 0, 0)));
//        Assert.assertEquals("Pill H2",5, gridBrowser.sumPill(new Pill(H, 1, 0)));
//        Assert.assertEquals("Pill H3",5, gridBrowser.sumPill(new Pill(H, 2, 0)));
//
//        Assert.assertEquals("Pill V1",2, gridBrowser.sumPill(new Pill(V, 0, 0)));
//        Assert.assertEquals("Pill V2",1, gridBrowser.sumPill(new Pill(V, 1, 0)));
//        Assert.assertEquals("Pill V3",2, gridBrowser.sumPill(new Pill(V, 2, 0)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkSumBadPills() throws Exception {
        GridBrowser gridBrowser = new GridBrowser(grid);
        Assert.assertEquals(5, gridBrowser.sumPill(new Pill(H, 3, 0)));
    }

    @Test
    public void checkGetColSum() throws Exception {
        GridBrowser gridBrowser = new GridBrowser(grid);
        gridBrowser.forEachCol((ints, c) -> {
            Assert.assertEquals(COLS_SUMS[c], gridBrowser.colSum(c));
        });
        gridBrowser.forEachRow((ints, r) -> {
            Assert.assertEquals(ROWS_SUMS[r], gridBrowser.rowSum(r));
        });

    }
}