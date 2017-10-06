package org.xflash.edd;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static org.junit.runners.Parameterized.Parameters;
import static org.xflash.edd.Pill.Orientation.H;
import static org.xflash.edd.Pill.Orientation.V;

@RunWith(Parameterized.class)
public class GridSolverFindingTest {

    public static final HashMap<Integer, Pair<Integer, Collection<Pill>>> EXPECTATIONS = new HashMap<>();
    private static Grid grid;

    private static class Pair<K,V> {
        public Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }

        K k;
        V v;
    }

    static {
        EXPECTATIONS.put(1, new Pair<>(5, Arrays.asList(
                new Pill(H, 0, 2),
                new Pill(H, 1, 2),
                new Pill(H, 2, 2),
                new Pill(V, 0, 1),
                new Pill(V, 3, 0)
        )));
        EXPECTATIONS.put(2, new Pair<>(6, Arrays.asList(
                new Pill(H, 1, 1),
                new Pill(H, 1, 2),
                new Pill(V, 0, 0),
                new Pill(V, 0, 2),
                new Pill(V, 1, 0),
                new Pill(V, 1, 1),
                new Pill(V, 3, 1)
        )));
    }

    private int num;

    public GridSolverFindingTest(int num) {
        this.num = num;
    }


    @BeforeClass
    public static void setUpClass() throws Exception {
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

    @Parameters
    public static Collection<Integer> data() {
        return EXPECTATIONS.keySet();
//        return Arrays.asList(1, 2, 3, 4, 5);
    }

    @Test
    public void checkFinding() throws Exception {
        GridSolver gridSolver = new GridSolver(grid);
        Set<Pill> allFor = gridSolver.findAllPillsMatching(num);

        Pair<Integer, Collection<Pill>> expectatedPills = EXPECTATIONS.get(num);
        Assert.assertEquals(allFor.size(), expectatedPills.k.intValue());
        for (Pill pill : expectatedPills.v) {
            Assert.assertTrue("Check if pill "+pill+" is found for number                                                          "+ num, allFor.contains(pill));
        }
        //Assert.assertEquals(EXPECTATIONS.get(num));

    }

}