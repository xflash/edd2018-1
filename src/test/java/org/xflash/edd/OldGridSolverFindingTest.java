package org.xflash.edd;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.Pill;
import org.xflash.edd.reader.GridBuilder;
import org.xflash.edd.solver.OldGridSolver;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static org.junit.runners.Parameterized.Parameters;
import static org.xflash.edd.model.Pill.Orientation.H;
import static org.xflash.edd.model.Pill.Orientation.V;

@RunWith(Parameterized.class)
public class OldGridSolverFindingTest {

    public static final HashMap<Integer, Collection<Pill>> EXPECTATIONS = new HashMap<>();
    private static Grid grid;



    static {
        EXPECTATIONS.put(1, Arrays.asList(
                new Pill(H, 0, 2),
                new Pill(H, 1, 2),
                new Pill(H, 2, 2),
                new Pill(V, 0, 1),
                new Pill(V, 3, 0)
        ));
        EXPECTATIONS.put(2, Arrays.asList(
                new Pill(H, 1, 1),
                new Pill(H, 1, 2),
                new Pill(V, 0, 0),
                new Pill(V, 0, 2),
                new Pill(V, 1, 0),
                new Pill(V, 1, 1),
                new Pill(V, 3, 1)
        ));
    }

    private int num;

    public OldGridSolverFindingTest(int num) {
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
        OldGridSolver gridSolver = new OldGridSolver();
        Set<Pill> allFor = gridSolver.findAllPillsMatching(num, new GridBrowser(grid));

        Collection<Pill> expectatedPills = EXPECTATIONS.get(num);
        Assert.assertEquals("Check " + allFor, allFor.size(), expectatedPills.size());
        for (Pill pill : expectatedPills) {
            Assert.assertTrue("Check if pill " + pill + " is found for number " + num + " in " + allFor,
                    allFor.contains(pill));
        }
        //Assert.assertEquals(EXPECTATIONS.get(num));

    }

}