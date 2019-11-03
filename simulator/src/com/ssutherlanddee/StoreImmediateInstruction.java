package com.ssutherlanddee;

public class StoreImmediateInstruction extends LoadStoreInstruction {

    private Integer immediate;
    private Integer offset;

    public StoreImmediateInstruction(Integer sourceRegister, Integer offset, Integer immediate) {
        super(Instruction.Opcode.sti, 1, -1, sourceRegister);
        this.immediate = immediate;
        this.offset = offset;
    }

    @Override
    public void execute(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.sourceValue + this.offset, this.immediate);
    }

    @Override
    public String toString() {
        return Instruction.Opcode.sti + " r" + this.destinationRegister + " " + this.offset + " " + this.immediate;
    }

}
