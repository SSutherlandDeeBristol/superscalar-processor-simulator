package com.ssutherlanddee;

public class StoreImmediateInstruction extends LoadStoreInstruction {

    private Integer immediate;
    private Integer offset;

    public StoreImmediateInstruction(Integer sourceRegisterA, Integer offset, Integer immediate) {
        super(Instruction.Opcode.sti, 1, -1, sourceRegisterA, -1);
        this.immediate = immediate;
        this.offset = offset;
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.sourceValueA + this.offset, this.immediate);
    }

    @Override
    public String toString() {
        return Instruction.Opcode.sti + " r" + this.sourceRegisterA + " " + this.offset + " " + this.immediate + " | " + this.state;
    }

}
