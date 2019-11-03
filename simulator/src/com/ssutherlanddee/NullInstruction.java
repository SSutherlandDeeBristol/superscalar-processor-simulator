package com.ssutherlanddee;

import java.util.Collections;
import java.util.List;

public class NullInstruction extends Instruction {

    public NullInstruction() {
        super(Opcode.nop, 1);
    }

    @Override
    public void execute(Processor processor) {}

    @Override
    public List<Integer> registerOperands() {
        return Collections.emptyList();
    }

    @Override
    public void setDestinationValid(RegisterFile registerFile, boolean valid) {}

    @Override
    public void setOperands(RegisterFile registerFile) {}

    @Override
    public void writeBack(RegisterFile registerFile) {}

    @Override
    public String toString() {
        return this.getOpcode().toString();
    }

}