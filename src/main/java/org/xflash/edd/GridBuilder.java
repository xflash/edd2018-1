package org.xflash.edd;

public class GridBuilder {

    private final Grid builded;

    private GridBuilder(int sz) {
        builded = new Grid(sz, sz);
    }

    public static GridBuilder with(int sz) {
        return new GridBuilder(sz);
    }

    public GridBuilder colsSums(int[] colsSums) {
        builded.colsSums = colsSums;
        return this;
    }

    public GridBuilder cells(int[][] grid) {
        builded.cells = grid;
        return this;
    }

    public GridBuilder rowsSums(int[] rowsSums) {
        builded.rowsSums = rowsSums;
        return this;
    }

    public Grid get() {
        return builded;
    }
}
