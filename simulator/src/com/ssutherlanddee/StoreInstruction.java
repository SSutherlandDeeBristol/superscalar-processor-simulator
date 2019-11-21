package com.ssutherlanddee;

import com.ssutherlanddee.Operand.OperandType;

public class StoreInstruction extends LoadStoreInstruction {

    public StoreInstruction(Operand[] operands, Integer tag) {
        super(Instruction.Opcode.st, 3, tag, operands);
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.destination.getType() == OperandType.TAG && this.destination.getContents() == tag)
            this.destination.setType(OperandType.VALUE, value);
        if (this.sourceA.getType() == OperandType.TAG && this.sourceA.getContents() == tag)
            this.sourceA.setType(OperandType.VALUE, value);
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {
        if (this.destination.getType() == OperandType.REGISTER)
            this.destination = updateRegisterOperand(this.destination, registerFile, reorderBuffer);
        if (this.sourceA.getType() == OperandType.REGISTER)
            this.sourceA = updateRegisterOperand(this.sourceA, registerFile, reorderBuffer);
    }

    @Override
    public boolean ready(RegisterFile registerFile) {
        return (this.destination.isReady() && this.sourceA.isReady());
    }

    @Override
    public void blockDestination(RegisterFile registerFile) {}

    @Override
    public void freeDestination(RegisterFile registerFile) {}

    @Override
    public void writeBack(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.sourceA.getContents() + this.sourceB.getContents(),
            this.destination.getContents());
    }
}
