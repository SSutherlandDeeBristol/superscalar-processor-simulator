package com.ssutherlanddee;

public class LoadStoreInstruction extends Instruction {

    public LoadStoreInstruction(Opcode opcode, Integer delay) {
        super(opcode, delay);
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {

    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return null;
    }
}
