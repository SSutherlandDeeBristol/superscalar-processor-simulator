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

    @Override
    public String toString() {
        return String.format(Opcode.addi + " r%d r%d %d | %s", this.destinationRegister, this.sourceRegisterA, this.immediate, this.state);
    }
}