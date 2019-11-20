package com.ssutherlanddee;

public class BranchLessThanEqualInstruction extends BranchInstruction {

    public BranchLessThanEqualInstruction(Operand[] operands, Integer tag) {
        super(Opcode.ble, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        System.out.print(this.stringRepresentation + " operands " + this.sourceA.toString() + " " + this.sourceB.toString());
        if (this.sourceA.getContents() <= this.sourceB.getContents()) {
            this.branchTo = this.PC + this.offset.getContents();
            this.shouldBranch = true;
        }
        this.state = State.EXECUTING;
    }
}
