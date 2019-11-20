package com.ssutherlanddee;

public class LoadImmediateInstruction extends LoadStoreInstruction {

    public LoadImmediateInstruction(Operand[] operands, Integer tag) {
        super(Opcode.ldi, 2, tag, operands);
    }

    @Override
    public void writeBack(Processor processor) {
        //processor.getRegisterFile().getRegister(this.destination.getContents()).set(this.sourceA.getContents());
    }

    @Override
    public Integer getResult() {
        return this.sourceA.getContents();
    }
}
