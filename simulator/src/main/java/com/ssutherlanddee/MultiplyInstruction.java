package com.ssutherlanddee;

public class MultiplyInstruction extends ALUInstruction {

    public MultiplyInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.MUL, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA * this.operandValB;
    }

}
