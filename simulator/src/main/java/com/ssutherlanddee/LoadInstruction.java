package com.ssutherlanddee;

public class LoadInstruction extends LoadStoreInstruction {

    private Integer destinationRegister;
    private Integer sourceRegister;
    private Integer offset;
    private Integer toLoad;

    public LoadInstruction(Integer destinationRegister, Integer sourceRegister, Integer offset) {
        super(Opcode.LD, 1);
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
        this.offset = offset;
        this.toLoad = 0;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        Integer sourceRegisterValue = processor.getRegisterFile().getRegister(this.sourceRegister).get();
        this.toLoad = (Integer) processor.getMemory().getMemoryByAddress(sourceRegisterValue + this.offset);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(this.toLoad);
    }

    @Override
    public String toString() {
        return Opcode.LD + " r" + this.destinationRegister + " r" + this.sourceRegister + " " + this.offset;
    }
}
