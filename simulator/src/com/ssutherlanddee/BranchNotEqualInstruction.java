package com.ssutherlanddee;

public class BranchNotEqualInstruction extends BranchInstruction {

    public BranchNotEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.bne, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (!valueB.equals(valueA)) {
            this.branchTo = this.PC + this.offset;
            this.shouldBranch = true;
        }
    }
}
