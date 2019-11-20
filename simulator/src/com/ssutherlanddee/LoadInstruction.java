package com.ssutherlanddee;

import com.ssutherlanddee.Operand.OperandType;

public class LoadInstruction extends LoadStoreInstruction {

    public LoadInstruction(Operand[] operands, Integer tag) {
        super(Opcode.ld, 1, tag, operands);
    }

    @Override
    public void execute(Processor processor) {
        this.sourceB = new Operand(OperandType.VALUE, (Integer) processor.getMemory().getMemoryByAddress(this.sourceA.getContents() + this.sourceB.getContents()));
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destination.getContents()).set(this.sourceB.getContents());
    }

    @Override
    public Integer getResult() {
        return this.sourceB.getContents();
    }
}
