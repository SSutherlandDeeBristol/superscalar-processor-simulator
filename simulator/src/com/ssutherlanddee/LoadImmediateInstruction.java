package com.ssutherlanddee;

public class LoadImmediateInstruction extends LoadStoreInstruction {

    private Integer immediate;

    public LoadImmediateInstruction(Integer destinationRegister, Integer immediate) {
        super(Opcode.ldi, 2, destinationRegister, -1, -1);
        this.immediate = immediate;
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destinationRegister).set(this.immediate);
    }

    @Override
    public String toString() {
        return Opcode.ldi + " r" + this.destinationRegister + " " + this.immediate + " | " + this.state;
    }
}
