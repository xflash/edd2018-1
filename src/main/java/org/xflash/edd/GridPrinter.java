package org.xflash.edd;

import java.io.PrintStream;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GridPrinter {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BOLD = "\u001B[1m";

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String[] COLORS = new String[]{
            ANSI_RED,
            ANSI_BLUE,
            ANSI_YELLOW,
            ANSI_GREEN,
            ANSI_PURPLE,
            ANSI_CYAN,
            ANSI_BLACK
    };
    private final PrintStream printStream;
    private Grid grid;

    public GridPrinter(Grid grid, PrintStream printStream) {
        this.grid = grid;
        this.printStream = printStream;
    }

    public void print() {
        printColSums();
        printSepRow();
        printGridRow(c->{
            printStream.print("" + c);
        });
    }

    public void printWithPill(Collection<Pill> pills) {
        printColSums();
        printSepRow();
        printGridRow(c->{
            printStream.print("" + c);
        });
    }

    private void printGridRow(Consumer<Integer> cellPrinter) {
        new GridBrowser(grid)
                .forEachRow((rowCells, r) -> {
                    printStream.print("" + grid.rowsSums[r] + "|");
                    String sep = "";
                    for (int cell : rowCells) {
                        printStream.print(sep);
                        cellPrinter.accept(cell);
                        sep = " ";
                    }
                    printStream.println();
                });
    }

    private void printSepRow() {
        printStream.print(" ");
        String sep1 = "+-";
        for (int ignored : grid.colsSums) {
            printStream.print(sep1);
            sep1 = "--";
        }
        printStream.println();
    }

    private void printColSums() {
        printStream.print(" ");
        for (int i = 0; i < grid.colsSums.length; i++) {
            printStream.print(" " + grid.colsSums[i]);
        }
        printStream.println();
    }
}
