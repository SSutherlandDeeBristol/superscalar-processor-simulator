package com.ssutherlanddee;

import java.util.Arrays;
import java.util.List;

import com.ssutherlanddee.Operand.OperandType;

public class StoreImmediateInstruction extends LoadStoreInstruction {

    public StoreImmediateInstruction(Operand[] operands, Integer tag) {
        super(Instruction.Opcode.sti, 3, tag, operands);
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.destination.getType() == OperandType.TAG && this.destination.getContents() == tag) {
            this.destination.setType(OperandType.VALUE, value);
        }
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {
        if (this.destination.getType() == OperandType.REGISTER)
            this.destination = updateRegisterOperand(this.destination, registerFile, reorderBuffer);
    }

    @Override
    public boolean ready(RegisterFile registerFile) {
        return (this.destination.isReady());
    }

    @Override
    public void blockDestination(RegisterFile registerFile) {}

    @Override
    public void freeDestination(RegisterFile registerFile) {}

    @Override
    public void writeBack(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.destination.getContents() + this.sourceA.getContents(),
            this.sourceB.getContents());
    }
}
