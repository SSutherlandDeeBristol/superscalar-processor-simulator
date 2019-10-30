package com.ssutherlanddee;

public class StoreImmediateInstruction extends LoadStoreInstruction {

    private Integer immediate;
    private Integer destinationRegister;
    private Integer offset;

    public StoreImmediateInstruction(Integer destinationRegister, Integer offset, Integer immediate) {
        super(Instruction.Opcode.sti, 1);
        this.immediate = immediate;
        this.destinationRegister = destinationRegister;
        this.offset = offset;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        Integer baseAddress = processor.getRegisterFile().getRegister(this.destinationRegister).get();
        processor.getMemory().setMemoryByAddress(baseAddress + this.offset, this.immediate);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return Instruction.Opcode.sti + " r" + this.destinationRegister + " " + this.offset + " " + this.immediate;
    }

}
