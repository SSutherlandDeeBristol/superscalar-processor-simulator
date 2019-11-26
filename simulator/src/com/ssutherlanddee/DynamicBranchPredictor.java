package com.ssutherlanddee;

import java.util.Optional;

public class DynamicBranchPredictor extends BranchPredictor {

    private Integer historySize;

    public DynamicBranchPredictor(Integer historySize) {
        this.historySize = historySize;
    }

    @Override
    public Optional<Pair<Integer, Boolean>> predict(String encodedInstruction, Integer PC) {
        Optional<BranchTargetAddressCacheEntry> entry = this.BTAC.getEntry(PC);
        if (entry.isPresent()) {
            Pair<Integer, Boolean> branchInfo = entry.get().shouldTakeBranch(this.historySize);
            if (branchInfo.second())
                return Optional.of(branchInfo);
        }
        return Optional.empty();
    }
}