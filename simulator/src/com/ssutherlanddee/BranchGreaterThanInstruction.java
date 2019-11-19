package com.ssutherlanddee;

public class BranchGreaterThanInstruction extends BranchInstruction {

    public BranchGreaterThanInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.bgt, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA > valueB) {
            this.branchTo = this.PC + this.offset;
            this.shouldBranch = true;
        }
    }
}
