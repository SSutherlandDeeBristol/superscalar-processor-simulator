package com.ssutherlanddee;

import java.util.HashMap;
import java.util.Optional;

public class BranchTargetAddressCache {

    private HashMap<Integer, BranchTargetAddressCacheEntry> cache;
    private Integer historySize;

    public BranchTargetAddressCache(Integer historySize) {
        this.cache = new HashMap<>();
        this.historySize = historySize;
    }

    public void update(Integer PC, Integer targetAddress, boolean taken) {
        if (this.cache.containsKey(PC)) {
            this.cache.get(PC).update(taken);
        } else {
            this.cache.put(PC, new BranchTargetAddressCacheEntry(PC, targetAddress, taken, historySize));
        }
    }

    public Optional<BranchTargetAddressCacheEntry> getEntry(Integer PC) {
        if (this.cache.containsKey(PC))
            return Optional.of(this.cache.get(PC));
        return Optional.empty();
    }

    public void printContents() {
        for (Integer key : this.cache.keySet()) {
            this.cache.get(key).printContents();
        }
        System.out.println();
    }
}