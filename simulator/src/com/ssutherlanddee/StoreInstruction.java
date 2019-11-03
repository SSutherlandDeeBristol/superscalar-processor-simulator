package com.ssutherlanddee;

public class StoreInstruction extends LoadStoreInstruction {

    private Integer offset;
    private Integer baseAddress;

    public StoreInstruction(Integer sourceRegister, Integer destinationRegister, Integer offset) {
        super(Instruction.Opcode.st, 1, destinationRegister, sourceRegister);
        this.offset = offset;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        this.sourceValue = registerFile.getRegister(this.sourceRegister).get();
        this.baseAddress = registerFile.getRegister(this.destinationRegister).get();
    }

    @Override
    public void execute(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.baseAddress + this.offset, this.sourceValue);
    }

    @Override
    public String toString() {
        return Instruction.Opcode.st + " r" + this.sourceRegister + " r" + this.destinationRegister + " " + this.offset;
    }

}
