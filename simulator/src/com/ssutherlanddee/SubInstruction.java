package com.ssutherlanddee;

public class SubInstruction extends ALUInstruction {

    public SubInstruction(Operand[] operands, Integer tag) {
        super(Opcode.sub, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.sourceA.getContents() - this.sourceB.getContents();
        this.state = State.EXECUTING;
    }
}
