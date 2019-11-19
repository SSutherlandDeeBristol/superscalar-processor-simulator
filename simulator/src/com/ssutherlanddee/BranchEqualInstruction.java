package com.ssutherlanddee;

public class BranchEqualInstruction extends BranchInstruction {

    public BranchEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.beq, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueB.equals(valueA)) {
            this.branchTo = this.PC + this.offset;
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
