package com.ssutherlanddee;

public class LoadImmediateInstruction extends LoadStoreInstruction {

    private Integer destinationRegister;
    private Integer immediate;

    public LoadImmediateInstruction(Integer destinationRegister, Integer immediate) {
        super(Opcode.ldi, 1);
        this.destinationRegister = destinationRegister;
        this.immediate = immediate;
    }
    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {

    }

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(this.immediate);

    }

    @Override
    public String toString() {
        return Opcode.ldi + " r" + this.destinationRegister + " " + this.immediate;
    }
}
