package com.ssutherlanddee;

public class AddImmediateInstruction extends ALUInstruction {

    public AddImmediateInstruction(Operand[] operands, Integer tag) {
        super(Opcode.add, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.sourceA.getContents() + this.sourceB.getContents();
        this.state = State.EXECUTING;
    }
}