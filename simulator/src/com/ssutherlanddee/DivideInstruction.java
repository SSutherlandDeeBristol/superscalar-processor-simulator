package com.ssutherlanddee;

public class DivideInstruction extends ALUInstruction {

    public DivideInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.div, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA / this.operandValB;
    }
}
