package com.ssutherlanddee;

public class BranchEqualInstruction extends BranchInstruction {

    public BranchEqualInstruction(Operand[] operands, Integer tag) {
        super(Opcode.beq, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        if (this.sourceA.getContents().equals(this.sourceB.getContents())) {
            this.branchTo = this.PC + this.offset.getContents();
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
