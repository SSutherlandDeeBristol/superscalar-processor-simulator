package com.ssutherlanddee;

public class MultiplyInstruction extends ALUInstruction {

    public MultiplyInstruction(Operand[] operands, Integer tag) {
        super(Opcode.mul, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.sourceA.getContents() * this.sourceB.getContents();
        this.state = State.EXECUTING;
    }

}
