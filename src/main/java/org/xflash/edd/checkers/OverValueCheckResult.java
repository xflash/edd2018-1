package org.xflash.edd.checkers;

import org.xflash.edd.checkers.results.CheckResult;

/**
 * @author rcoqueugniot
 * @since 11.02.18
 */
public class OverValueCheckResult implements CheckResult {
    private final int colSum;
    private final int hintColSum;

    public OverValueCheckResult(int colSum, int hintColSum) {
        this.colSum = colSum;
        this.hintColSum = hintColSum;
    }

    public int getColSum() {
        return colSum;
    }

    public int getHintColSum() {
        return hintColSum;
    }
}
