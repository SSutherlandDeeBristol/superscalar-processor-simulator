package com.ssutherlanddee;

import java.util.Optional;

public abstract class BranchPredictor {

    protected BranchTargetAddressCache BTAC;

    public BranchPredictor(Integer historySize) {
        this.BTAC = new BranchTargetAddressCache(historySize);
    }

    public abstract Optional<Pair<Integer, Boolean>> predict(String encodedInstruction, Integer PC);

    public void update(Integer PC, Integer targetAddress, boolean taken) {
        this.BTAC.update(PC, targetAddress, taken);
    }

    public void printCache() {
        this.BTAC.printContents();
    }
}