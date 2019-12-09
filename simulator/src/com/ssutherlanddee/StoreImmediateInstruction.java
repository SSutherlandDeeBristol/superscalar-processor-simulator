package com.ssutherlanddee;

import java.util.Optional;

import com.ssutherlanddee.Operand.OperandType;

public class StoreImmediateInstruction extends LoadStoreInstruction {

    public StoreImmediateInstruction(Operand[] operands, Integer tag) {
        super(Instruction.Opcode.sti, 2, tag, operands);
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.destination.getType() == OperandType.TAG && this.destination.getContents() == tag) {
            this.destination.setType(OperandType.VALUE, value);
        }
        calculateAddress();
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {
        if (this.destination.getType() == OperandType.REGISTER)
            this.destination = updateRegisterOperand(this.destination, registerFile, reorderBuffer);
        calculateAddress();
    }

    @Override
    public boolean ready(RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        return this.destination.isReady();
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
        processor.getMemory().setMemoryByAddress(this.destination.getContents() + this.sourceA.getContents(),
            this.sourceB.getContents());
    }

    @Override
    protected void calculateAddress() {
        if (this.destination.isReady())
            this.address = Optional.of(this.destination.getContents() + this.sourceA.getContents());
    }

    @Override
    public Optional<Integer> getStoreAddress() {
        return this.address;
    }

    @Override
    public String getSourceOperandStatus() {
        return this.destination.toString();
    }
}
