package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

public class BranchTargetAddressCacheEntry {

    private Integer address;
    private Integer targetAddress;
    private List<Boolean> history;

    public BranchTargetAddressCacheEntry(Integer address, Integer targetAddress, boolean taken) {
        this.address = address;
        this.targetAddress = targetAddress;
        this.history = new ArrayList<>();
        this.history.add(0, taken);
    }

    public BranchTargetAddressCacheEntry(Integer targetAddress) {
        this.targetAddress = targetAddress;
        this.history = new ArrayList<>();
    }

    public void update(boolean taken) {
        this.history.add(0, taken);
    }

    public Integer getTargetAddress() {
        return this.targetAddress;
    }

    public void printContents() {
        String historyString = "";
        for (Boolean h : this.history) {
            historyString = historyString.concat((h) ? "1" : "0");
        }
        System.out.println(String.format("%3d | %3d | %s", this.address, this.targetAddress, historyString));
    }

    public Pair<Integer, Boolean> shouldTakeBranch(Integer numEntries) {
        Integer numTaken = 0;
        for (int i = 0; i < numEntries; i++) {
            if ((this.history.size() - 1) < i)
                break;
            if (this.history.get(i).booleanValue())
                numTaken++;
        }
        if (numTaken > Math.floor(numEntries / 2))
            return new Pair<Integer, Boolean>(this.targetAddress, true);
        return new Pair<Integer, Boolean>(this.address + 1, false);
    }
}