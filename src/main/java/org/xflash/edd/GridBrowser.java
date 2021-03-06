package org.xflash.edd;

import org.xflash.edd.checkers.GridPillUtils;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.Pill;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.xflash.edd.model.Pill.Orientation.H;
import static org.xflash.edd.model.Pill.Orientation.V;

public class GridBrowser {
    private final Grid grid;

    public GridBrowser(Grid grid) {

        this.grid = grid;
    }

    public void forEachPill(Consumer<Pill> consumer) {
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
        if (p.o.equals(H) && p.x > getMaxPillOffset()
                || p.o.equals(V) && p.y > getMaxPillOffset())
            throw new IllegalArgumentException("This pill is out of bound " + p);
        if (H.equals(p.o)) {
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

    public boolean isOverlapping(Pill p, Set<Pill> pills) {
        for (Pill pill : pills) {
            if (isOverlapping(p, pill)) return true;
        }
        return false;
    }

    public boolean isOverlapping(Pill p, Pill pill) {
        if (p.H() && (pill.x == p.x - 1 || pill.x == p.x - 2))
            return true;
        if (p.V() && (pill.y == p.y - 1 || pill.y == p.y - 2))
            return true;
        return false;
    }

    public boolean isPillCorrectSum(Pill pill) {
        return sum4(pill) == sumPill(pill);
    }

    public void forEachValuedPill(int v, Consumer<Pill> pillConsumer) {
        int maxW = grid.w - 3;
        int maxH = grid.h - 3;
        for (int x = 0; x <= maxW; x++) {
            for (int y = 0; y < grid.h; y++) {
                Pill p = new Pill(Pill.Orientation.H, x, y);
                if (GridPillUtils.computePillValInGrid(p, grid) == v)
                    pillConsumer.accept(p);
            }
        }
        for (int x = 0; x < grid.w; x++) {
            for (int y = 0; y <= maxH; y++) {
                Pill p = new Pill(Pill.Orientation.V, x, y);
                if (GridPillUtils.computePillValInGrid(p, grid) == v)
                    pillConsumer.accept(p);
            }
        }

    }
}
