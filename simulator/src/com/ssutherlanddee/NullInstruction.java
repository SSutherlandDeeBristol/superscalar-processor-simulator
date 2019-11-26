package com.ssutherlanddee;

import java.util.Arrays;
import java.util.List;

import com.ssutherlanddee.Operand.OperandType;

public class NullInstruction extends Instruction {

    public NullInstruction(Integer tag) {
        super(Opcode.nop, 1, tag, new Operand[] {new Operand(OperandType.NONE),
                new Operand(OperandType.NONE), new Operand(OperandType.NONE)});
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {}

    @Override
    public void blockDestination(RegisterFile registerFile) {}

    @Override
    public void freeDestination(RegisterFile registerFile) {}

    @Override
    public void execute(Processor processor) {
        this.state = State.EXECUTING;
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {}

    @Override
    public boolean ready(RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        return true;
    }

    @Override
    public void writeBack(Processor processor) {}

    @Override
    public Integer getResult() {
        return 0;
    }

    @Override
    public String getSourceOperandStatus() {
        return "";
    }

    @Override
    public String toString() {
        return this.getOpcode().toString();
    }

}