package com.ssutherlanddee;

import java.util.Optional;

public class DynamicBranchPredictor extends BranchPredictor {

    public DynamicBranchPredictor(Integer historySize) {
        super(historySize);
    }

    @Override
    public Optional<Pair<Integer, Boolean>> predict(String encodedInstruction, Integer PC) {
        Optional<BranchTargetAddressCacheEntry> entry = this.BTAC.getEntry(PC);
        if (entry.isPresent()) {
            if (encodedInstruction.startsWith("jmp"))
                return Optional.of(new Pair<Integer, Boolean>(entry.get().getTargetAddress(), true));
            Pair<Integer, Boolean> branchInfo = entry.get().shouldTakeBranch();
            if (branchInfo.second())
                return Optional.of(branchInfo);
        }
        return Optional.empty();
    }
}