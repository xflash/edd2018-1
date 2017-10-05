package org.xflash.edd;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.xflash.edd.Pill.Orientation.H;
import static org.xflash.edd.Pill.Orientation.V;

class GridBrowser {
    private final Grid grid;

    public GridBrowser(Grid grid) {

        this.grid = grid;
    }

    void forEachPill(Consumer<Pill> consumer) {
        int nb = grid.cells.length / 3;
        forEachRow((row, r) -> {
            for (int i = 0; i <= nb + 1; i++) {
                consumer.accept(new Pill(H, i, r));
            }
        });
        forEachCol((col, c) -> {
            for (int i = 0; i <= nb + 1; i++) {
                consumer.accept(new Pill(V, c, i));
            }
        });
    }

    void forEachRow(BiConsumer<int[], Integer> rowConsumer) {
        for (int i = 0; i < grid.cells.length; i++) {
            rowConsumer.accept(grid.cells[i], i);
        }
    }

    void forEachCol(BiConsumer<int[], Integer> colConsumer) {
        int length = grid.cells.length;
        for (int i = 0; i < length; i++) {
            int[] col = new int[length];
            for (int r = 0; r < length; r++) {
                col[r] = grid.cells[r][i];
            }
            colConsumer.accept(col, i);
        }
    }
}
