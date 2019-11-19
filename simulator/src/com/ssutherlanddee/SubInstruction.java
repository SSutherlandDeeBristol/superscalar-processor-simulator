package com.ssutherlanddee;

public class SubInstruction extends ALUInstruction {

    public SubInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.sub, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA - this.operandValB;
        this.state = State.EXECUTING;
    }
}
