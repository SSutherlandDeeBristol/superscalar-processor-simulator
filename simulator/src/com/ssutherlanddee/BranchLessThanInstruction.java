package com.ssutherlanddee;

public class BranchLessThanInstruction extends BranchInstruction {

    public BranchLessThanInstruction(Operand[] operands, Integer tag) {
        super(Opcode.blt, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        if (this.sourceA.getContents() < this.sourceB.getContents()) {
            this.branchTo = this.PC + this.offset.getContents();
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
