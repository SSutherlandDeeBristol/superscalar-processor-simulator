package com.ssutherlanddee;

public class SubImmediateInstruction extends ALUInstruction {

    public SubImmediateInstruction(Operand[] operands, Integer tag) {
        super(Opcode.add, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.sourceA.getContents() - this.sourceB.getContents();
        this.state = State.EXECUTING;
    }
}