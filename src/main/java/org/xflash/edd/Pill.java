package org.xflash.edd;

import static org.xflash.edd.Pill.Orientation.V;

public class Pill {

    final int x;
    final int y;
    final Orientation orientation;

    public Pill(Orientation orientation, int x, int y) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pill pill = (Pill) o;

        if (x != pill.x) return false;
        if (y != pill.y) return false;
        return orientation == pill.orientation;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pill{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }

    public boolean V() {
        return V.equals(orientation);
    }

    public boolean H() {
        return Orientation.H.equals(orientation);
    }

    public static enum Orientation {
        H, V
    }
}
