package com.ssutherlanddee;

public class AddInstruction extends ALUInstruction {

    public AddInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.add, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA + this.operandValB;
    }
}