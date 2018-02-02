package org.xflash.edd.model;

public class Grid {
    public final int w;
    public final int h;
    public int[] colsSums;
    public int[] rowsSums;
    public int[][] cells;

    public Grid(int w, int h) {
        this.w = w;
        this.h = h;
    }
}
