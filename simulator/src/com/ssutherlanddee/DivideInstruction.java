package com.ssutherlanddee;

public class DivideInstruction extends ALUInstruction {

    public DivideInstruction(Operand[] operands, Integer tag) {
        super(Opcode.div, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.sourceA.getContents() / this.sourceB.getContents();
        this.state = State.EXECUTING;
    }
}
