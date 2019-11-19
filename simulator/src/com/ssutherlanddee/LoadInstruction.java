package com.ssutherlanddee;

public class LoadInstruction extends LoadStoreInstruction {

    private Integer offset;
    private Integer toLoad;

    public LoadInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer offset) {
        super(Opcode.ld, 1, destinationRegister, sourceRegisterA, -1);
        this.offset = offset;
        this.toLoad = 0;
    }

    @Override
    public void execute(Processor processor) {
        this.toLoad = (Integer) processor.getMemory().getMemoryByAddress(this.sourceValueA + this.offset);
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destinationRegister).set(this.toLoad);
    }

    @Override
    public String toString() {
        return Opcode.ld + " r" + this.destinationRegister + " r" + this.sourceRegisterA + " " + this.offset;
    }
}
