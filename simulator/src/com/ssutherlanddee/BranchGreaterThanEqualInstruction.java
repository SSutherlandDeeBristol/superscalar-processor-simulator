package com.ssutherlanddee;

public class BranchGreaterThanEqualInstruction extends BranchInstruction {

    public BranchGreaterThanEqualInstruction(Operand[] operands, Integer tag) {
        super(Opcode.bge, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.branchTarget = this.PC + this.offset.getContents();
        if (this.sourceA.getContents() >= this.sourceB.getContents()) {
            this.branchTo = this.branchTarget;
            this.shouldBranch = true;
        } else {
            this.branchTo = this.PC + 1;
        }
        this.state = State.EXECUTING;
    }
}
