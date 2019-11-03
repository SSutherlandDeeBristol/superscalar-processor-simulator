package com.ssutherlanddee;

public class AddImmediateInstruction extends ALUInstruction {

    private Integer immediate;

    public AddImmediateInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer immediate) {
        super(Opcode.add, destinationRegister, sourceRegisterA, -1, 1);
        this.immediate = immediate;
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA + this.immediate;
    }
}