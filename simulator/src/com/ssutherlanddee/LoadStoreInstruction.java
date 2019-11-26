package com.ssutherlanddee;

import java.util.Optional;

import com.ssutherlanddee.Operand.OperandType;

public class LoadStoreInstruction extends Instruction {

    protected Operand destination;
    protected Operand sourceA;
    protected Operand sourceB;

    protected Optional<Integer> address;

    public LoadStoreInstruction(Opcode opcode, Integer delay, Integer tag, Operand[] operands) {
        super(opcode, delay, tag, operands);
        this.destination = operands[0];
        this.sourceA = operands[1];
        this.sourceB = operands[2];
        this.address = Optional.empty();
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.sourceA.getType() == OperandType.TAG && this.sourceA.getContents() == tag)
            this.sourceA.setType(OperandType.VALUE, value);
        if (this.sourceB.getType() == OperandType.TAG && this.sourceB.getContents() == tag)
            this.sourceB.setType(OperandType.VALUE, value);
        calculateAddress();
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {
        if (this.sourceA.getType() == OperandType.REGISTER)
            this.sourceA = updateRegisterOperand(this.sourceA, registerFile, reorderBuffer);
        if (this.sourceB.getType() == OperandType.REGISTER)
            this.sourceB = updateRegisterOperand(this.sourceB, registerFile, reorderBuffer);
        calculateAddress();
    }

    @Override
    public boolean ready(RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        return (this.sourceA.isReady() && this.sourceB.isReady());
    }

    @Override
    public void blockDestination(RegisterFile registerFile) {
        registerFile.getRegister(this.destination.getContents()).block(this.tag);
    }

    @Override
    public void freeDestination(RegisterFile registerFile) {
        registerFile.getRegister(this.destination.getContents()).free(this.tag);
    }

    @Override
    public void execute(Processor processor) {
        this.state = State.EXECUTING;
    }

    @Override
    public Integer getResult() {
        return 0;
    }

    @Override
    public String getSourceOperandStatus() {
        return this.sourceA.toString() + " " + this.sourceB.toString();
    }

    @Override
    public void writeBack(Processor processor) {}

    protected void calculateAddress() {}

    public Optional<Integer> getStoreAddress() {
        return Optional.empty();
    }
}
