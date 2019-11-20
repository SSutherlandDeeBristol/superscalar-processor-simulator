package com.ssutherlanddee;

public class JumpInstruction extends BranchInstruction {

    public JumpInstruction(Operand[] operands, Integer tag) {
        super(Opcode.jmp, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.shouldBranch = true;
        this.branchTo = this.offset.getContents();
        this.state = State.EXECUTING;
    }
}
