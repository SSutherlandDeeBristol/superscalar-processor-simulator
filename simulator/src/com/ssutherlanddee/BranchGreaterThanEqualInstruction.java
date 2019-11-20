package com.ssutherlanddee;

public class BranchGreaterThanEqualInstruction extends BranchInstruction {

    public BranchGreaterThanEqualInstruction(Operand[] operands, Integer tag) {
        super(Opcode.bge, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        if (this.sourceA.getContents() >= this.sourceB.getContents()) {
            this.branchTo = this.PC + this.offset.getContents();
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
