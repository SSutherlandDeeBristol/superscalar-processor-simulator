package com.ssutherlanddee;

import java.util.Arrays;
import java.util.List;

import com.ssutherlanddee.Operand.OperandType;

public class LoadStoreInstruction extends Instruction {

    protected Operand destination;
    protected Operand sourceA;
    protected Operand sourceB;

    public LoadStoreInstruction(Opcode opcode, Integer delay, Integer tag, Operand[] operands) {
        super(opcode, delay, tag, operands);
        this.destination = operands[0];
        this.sourceA = operands[1];
        this.sourceB = operands[2];
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.sourceA.getType() == OperandType.TAG && this.sourceA.getContents() == tag)
            this.sourceA.setType(OperandType.VALUE, value);
        if (this.sourceB.getType() == OperandType.TAG && this.sourceB.getContents() == tag)
            this.sourceB.setType(OperandType.VALUE, value);
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        if (this.sourceA.getType() == OperandType.REGISTER)
            this.sourceA = updateRegisterOperand(this.sourceA, registerFile, reorderBuffer);
        if (this.sourceB.getType() == OperandType.REGISTER)
            this.sourceB = updateRegisterOperand(this.sourceB, registerFile, reorderBuffer);
    }

    @Override
    public boolean ready(RegisterFile registerFile) {
        return (this.sourceA.isReady() && this.sourceB.isReady());
    }

    @Override
    public void blockDestination(RegisterFile registerFile) {
        registerFile.getRegister(this.destination.getContents()).block(this.tag);
    }

    @Override
    public void freeDestination(RegisterFile registerFile) {
        registerFile.getRegister(this.destination.getContents()).free();
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
    public void writeBack(Processor processor) {}
}
