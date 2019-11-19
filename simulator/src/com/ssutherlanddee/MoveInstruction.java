package com.ssutherlanddee;

public class MoveInstruction extends LoadStoreInstruction {

    public MoveInstruction(Integer destinationRegister, Integer sourceRegisterA) {
        super(Opcode.mov, 1, destinationRegister, sourceRegisterA, -1);
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destinationRegister).set(this.sourceValueA);
    }

    @Override
    public String toString() {
        return Opcode.mov + " r" + this.destinationRegister + " r" + this.sourceRegisterA;
    }
}
