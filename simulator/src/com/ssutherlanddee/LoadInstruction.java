package com.ssutherlanddee;

public class LoadInstruction extends LoadStoreInstruction {

    private Integer offset;
    private Integer toLoad;

    public LoadInstruction(Integer destinationRegister, Integer sourceRegister, Integer offset) {
        super(Opcode.ld, 1, destinationRegister, sourceRegister);
        this.offset = offset;
        this.toLoad = 0;
    }

    @Override
    public void execute(Processor processor) {
        this.toLoad = (Integer) processor.getMemory().getMemoryByAddress(this.sourceValue + this.offset);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(this.toLoad);
    }

    @Override
    public String toString() {
        return Opcode.ld + " r" + this.destinationRegister + " r" + this.sourceRegister + " " + this.offset;
    }
}
