package com.ssutherlanddee;

public class LoadIndexedInstruction extends LoadStoreInstruction {

    private Integer result;

    public LoadIndexedInstruction(Operand[] operands, Integer tag) {
        super(Opcode.ldx, 2, tag, operands);
        this.result = 0;
    }

    @Override
    public boolean ready(RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        return (this.sourceA.isReady() && this.sourceB.isReady() &&
                !(reorderBuffer.pollForStoreAddress(this.tag, this.sourceA.getContents() + this.sourceB.getContents())));
    }

    @Override
    public void execute(Processor processor) {
        this.result = (Integer) processor.getMemory().getMemoryByAddress(this.sourceA.getContents() + this.sourceB.getContents());
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destination.getContents()).set(this.result);
    }

    @Override
    public Integer getResult() {
        return this.result;
    }
}
