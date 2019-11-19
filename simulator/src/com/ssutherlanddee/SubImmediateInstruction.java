package com.ssutherlanddee;

public class SubImmediateInstruction extends ALUInstruction {

    private Integer immediate;

    public SubImmediateInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer immediate) {
        super(Opcode.add, destinationRegister, sourceRegisterA, -1, 1);
        this.immediate = immediate;
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA - this.immediate;
    }

    @Override
    public String toString() {
        return String.format(Opcode.subi + " r%d r%d %d", this.destinationRegister, this.sourceRegisterA, this.immediate);
    }
}