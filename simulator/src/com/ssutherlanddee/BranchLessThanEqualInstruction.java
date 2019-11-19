package com.ssutherlanddee;

public class BranchLessThanEqualInstruction extends BranchInstruction {

    public BranchLessThanEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.ble, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA <= valueB) {
            this.branchTo = this.PC + this.offset;
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
