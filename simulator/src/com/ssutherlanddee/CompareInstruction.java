package com.ssutherlanddee;

public class CompareInstruction extends ALUInstruction {

    public CompareInstruction(Operand[] operands, Integer tag) {
        super(Opcode.cmp, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        if (this.sourceA.getContents() < this.sourceB.getContents()) {
            this.result = -1;
        } else if (this.sourceA.getContents() > this.sourceB.getContents()) {
            this.result = 1;
        } else {
            this.result = 0;
        }

        this.state = State.EXECUTING;
    }
}
