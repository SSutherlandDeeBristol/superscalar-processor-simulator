package com.ssutherlanddee;

public class MoveInstruction extends LoadStoreInstruction {

    public MoveInstruction(Integer destinationRegister, Integer sourceRegister) {
        super(Opcode.mov, 1, destinationRegister, sourceRegister);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(this.sourceValue);
    }

    @Override
    public String toString() {
        return Opcode.mov + " r" + this.destinationRegister + " r" + this.sourceRegister;
    }
}
