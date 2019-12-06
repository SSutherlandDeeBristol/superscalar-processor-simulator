package com.ssutherlanddee;

public class MoveInstruction extends ALUInstruction {

    public MoveInstruction(Operand[] operands, Integer tag) {
        super(Opcode.mov, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.sourceA.getContents();
        this.state = State.EXECUTING;
    }
}
