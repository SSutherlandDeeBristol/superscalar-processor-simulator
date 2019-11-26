package com.ssutherlanddee;

public class EncodedInstruction {

    private Integer PC;
    private String instruction;
    private boolean predictedBranch;

    public EncodedInstruction(Integer PC, String instruction, boolean predictedBranch) {
        this.PC = PC;
        this.instruction = instruction;
        this.predictedBranch = predictedBranch;
    }

    public Integer getPC() {
        return this.PC;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public boolean getPredictedBranch() {
        return this.predictedBranch;
    }
}