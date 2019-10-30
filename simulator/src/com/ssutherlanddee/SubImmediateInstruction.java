package com.ssutherlanddee;

public class SubImmediateInstruction extends ALUInstruction {

    private Integer immediate;

    public SubImmediateInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer immediate) {
        super(Opcode.add, destinationRegister, sourceRegisterA, 0, 1);
        this.immediate = immediate;
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA - this.immediate;
    }
}