package com.ssutherlanddee;

public class BranchNotEqualInstruction extends BranchInstruction {

    public BranchNotEqualInstruction(Operand[] operands, Integer tag) {
        super(Opcode.bne, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        if (!this.sourceA.getContents().equals(this.sourceB.getContents())) {
            this.branchTo = this.PC + this.offset.getContents();
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
