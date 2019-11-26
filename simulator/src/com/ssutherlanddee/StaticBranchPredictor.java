package com.ssutherlanddee;

import java.util.Optional;

public class StaticBranchPredictor extends BranchPredictor {

    public StaticBranchPredictor() {}

    @Override
    public Optional<Pair<Integer, Boolean>> predict(String encodedInstruction, Integer PC) {
        if (encodedInstruction.contains("jmp")) {
            Optional<BranchTargetAddressCacheEntry> entry = this.BTAC.getEntry(PC);
            if (entry.isPresent())
                return Optional.of(new Pair<Integer, Boolean>(entry.get().getTargetAddress(), true));
        }
        return Optional.empty();
    }
}