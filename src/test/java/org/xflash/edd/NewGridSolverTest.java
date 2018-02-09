package org.xflash.edd;

import org.junit.Before;
import org.junit.Test;
import org.xflash.edd.model.Grid;
import org.xflash.edd.reader.GridReader;

/**
 * @author rcoqueugniot
 * @since 02.02.18
 */
public class NewGridSolverTest {

    private Grid grid;
    private GridBrowser gridBrowser;

    @Before
    public void setUp() {
        grid = GridReader.from(FileUtils.classpath("grid1.txt"));
        gridBrowser = new GridBrowser(grid);
    }

    @Test
    public void name() {
        for (int v = 4; v >= 0; v--) {
            final int v1 = v;
            gridBrowser.forEachValuedPill(v1, pill -> {
                System.out.println("Value = " + v1 + " pill " + pill);
            });
        }

    }
}