package org.xflash.edd;

public class GridBuilder {

    private final Grid builded;

    private GridBuilder(int w, int h) {
        builded = new Grid(w, h);
    }

    public static GridBuilder with(int w, int h) {
        return new GridBuilder(w, h);
    }

    public GridBuilder colsSums(int[] colsSums) {
        return this;
    }

    public GridBuilder cells(int[][] grid) {
        return this;
    }

    public GridBuilder rowsSums(int[] rowsSums) {
        return this;
    }

    public Grid get() {
        return builded;
    }
}
