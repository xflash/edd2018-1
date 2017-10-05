package org.xflash.edd;

import java.util.Set;
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
        int nb = getMaxPillOffset();
        forEachRow((row, r) -> {
            for (int i = 0; i <= nb; i++) {
                consumer.accept(new Pill(H, i, r));
            }
        });
        forEachCol((col, c) -> {
            for (int i = 0; i <= nb; i++) {
                consumer.accept(new Pill(V, c, i));
            }
        });
    }

    private int getMaxPillOffset() {
        return 1 + (grid.cells.length / 3);
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

    public int sumPill(Pill p) {
        int sum = 0;
        if (p.orientation.equals(H) && p.x > getMaxPillOffset()
                || p.orientation.equals(V) && p.y > getMaxPillOffset())
            throw new IllegalArgumentException("This pill is out of bound " + p);
        if (H.equals(p.orientation)) {
            sum += grid.cells[p.y][p.x + 0];
            sum += grid.cells[p.y][p.x + 1];
            sum += grid.cells[p.y][p.x + 2];
        } else {
            sum += grid.cells[p.y + 0][p.x];
            sum += grid.cells[p.y + 1][p.x];
            sum += grid.cells[p.y + 2][p.x];
        }
        return sum;
    }

    public int colSum(int x) {
        return grid.colsSums[x];
    }

    public int rowSum(int y) {
        return grid.rowsSums[y];
    }

    public int sum4(Pill p) {
        return p.V() ? colSum(p.x) : rowSum(p.y);
    }

    public boolean checkOverlaps(Pill p, Set<Pill> pills) {
        for (Pill pill : pills) {
            if (p.H() && (pill.x == p.x - 1 || pill.x == p.x - 2))
                return true;
            if (p.V() && (pill.y == p.y - 1 || pill.y == p.y - 2))
                return true;
        }
        return false;
    }
}
