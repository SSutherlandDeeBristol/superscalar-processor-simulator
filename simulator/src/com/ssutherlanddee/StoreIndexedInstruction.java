package com.ssutherlanddee;

import java.util.Optional;

import com.ssutherlanddee.Operand.OperandType;

public class StoreIndexedInstruction extends LoadStoreInstruction {

    public StoreIndexedInstruction(Operand[] operands, Integer tag) {
        super(Instruction.Opcode.stx, 2, tag, operands);
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.destination.getType() == OperandType.TAG && this.destination.getContents() == tag)
            this.destination.setType(OperandType.VALUE, value);
        if (this.sourceA.getType() == OperandType.TAG && this.sourceA.getContents() == tag)
            this.sourceA.setType(OperandType.VALUE, value);
        if (this.sourceB.getType() == OperandType.TAG && this.sourceB.getContents() == tag)
            this.sourceB.setType(OperandType.VALUE, value);
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {
        if (this.destination.getType() == OperandType.REGISTER)
            this.destination = updateRegisterOperand(this.destination, registerFile, reorderBuffer);
        if (this.sourceA.getType() == OperandType.REGISTER)
            this.sourceA = updateRegisterOperand(this.sourceA, registerFile, reorderBuffer);
        if (this.sourceB.getType() == OperandType.REGISTER)
            this.sourceB = updateRegisterOperand(this.sourceB, registerFile, reorderBuffer);
    }

    @Override
    public boolean ready(RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        return (this.destination.isReady() && this.sourceA.isReady() && this.sourceB.isReady());
    }

    @Override
    public void blockDestination(RegisterFile registerFile) {}

    @Override
    public void freeDestination(RegisterFile registerFile) {}

    @Override
    public void execute(Processor processor) {
        calculateAddress();
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getMemory().setMemoryByAddress(this.sourceA.getContents() + this.sourceB.getContents(),
            this.destination.getContents());
    }

    @Override
    public Optional<Integer> getStoreAddress() {
        return this.address;
    }

    @Override
    protected void calculateAddress() {
        if (this.sourceA.isReady() && this.sourceB.isReady())
            this.address = Optional.of(this.sourceA.getContents() + this.sourceB.getContents());
    }

    @Override
    public String getSourceOperandStatus() {
        return this.destination.toString() + " " + this.sourceA.toString() + " " + this.sourceB.toString();
    }
}
