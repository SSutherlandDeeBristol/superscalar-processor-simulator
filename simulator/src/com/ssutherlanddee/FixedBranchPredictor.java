package com.ssutherlanddee;

import java.util.Optional;

public class FixedBranchPredictor extends BranchPredictor {

    private boolean taken;

    public FixedBranchPredictor(boolean taken) {
        super(1);
        this.taken = taken;
    }

    @Override
    public Optional<Pair<Integer, Boolean>> predict(String encodedInstruction, Integer PC) {
        Optional<BranchTargetAddressCacheEntry> btacEntry = this.BTAC.getEntry(PC);

        if (btacEntry.isPresent()) {
            if (encodedInstruction.startsWith("jmp"))
                return Optional.of(new Pair<Integer, Boolean>(btacEntry.get().getTargetAddress(), true));
            if (!this.taken)
                return Optional.empty();
            return Optional.of(new Pair<Integer, Boolean>(btacEntry.get().getTargetAddress(), this.taken));
        }

        return Optional.empty();
    }
}