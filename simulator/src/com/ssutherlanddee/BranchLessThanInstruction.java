package com.ssutherlanddee;

public class BranchLessThanInstruction extends BranchInstruction {

    public BranchLessThanInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.blt, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA < valueB) {
            this.branchTo = this.PC + this.offset;
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
