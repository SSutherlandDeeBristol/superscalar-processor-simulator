package com.ssutherlanddee;

public class BranchLessThanEqualInstruction extends BranchInstruction {

    public BranchLessThanEqualInstruction(Operand[] operands, Integer tag) {
        super(Opcode.ble, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.branchTarget = this.PC + this.offset.getContents();
        if (this.sourceA.getContents() <= this.sourceB.getContents()) {
            this.branchTo = this.branchTarget;
            this.shouldBranch = true;
        } else {
            this.branchTo = this.PC + 1;
        }
        this.state = State.EXECUTING;
    }
}
