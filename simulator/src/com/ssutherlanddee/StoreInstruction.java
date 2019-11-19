package com.ssutherlanddee;

public class StoreInstruction extends LoadStoreInstruction {

    private Integer offset;

    public StoreInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Instruction.Opcode.st, 1, -1, sourceRegisterA, sourceRegisterB);
        this.offset = offset;
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.sourceValueB + this.offset, this.sourceValueA);
    }

    @Override
    public String toString() {
        return Instruction.Opcode.st + " r" + this.sourceRegisterA + " r" + this.sourceRegisterB + " " + this.offset + " | " + this.state;
    }

}
