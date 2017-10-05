package org.xflash.edd;

public class Grid {
    final int w;
    private final int h;
    public int[] colsSums;
    public int[] rowsSums;
    public int[][] cells;

    public Grid(int w, int h) {
        this.w = w;
        this.h = h;
    }
}
