package org.xflash.edd;

import static org.xflash.edd.Pill.Orientation.V;

public class Pill {

    public final int x;
    public final int y;
    public final Orientation o;

    public Pill(Orientation o, int x, int y) {
        this.x = x;
        this.y = y;
        this.o = o;
    }

    public static Pill hpill(int x, int y) {
        return new Pill(Orientation.H, x, y);
    }

    public static Pill vpill(int x, int y) {
        return new Pill(V, x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pill pill = (Pill) o;

        if (x != pill.x) return false;
        if (y != pill.y) return false;
        return this.o == pill.o;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (o != null ? o.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pill{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + o +
                '}';
    }

    public boolean V() {
        return V.equals(o);
    }

    public boolean H() {
        return Orientation.H.equals(o);
    }

    public static enum Orientation {
        H, V
    }
}
