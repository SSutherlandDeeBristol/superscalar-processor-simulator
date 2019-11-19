package com.ssutherlanddee;

import java.util.List;

public abstract class Instruction {
    public enum Opcode {nop, mov, cmp, bne, beq, bgt, blt, bge, ble, jmp, add, addi, sub, subi, mul, div, ld, ldi, st, sti}

    private Opcode opcode;
    private Integer numCycles;
    private boolean finishedExecuting;

    public Instruction(Opcode opcode, Integer delay) {
        this.opcode = opcode;
        this.numCycles = delay;
        this.finishedExecuting = false;
    }

    public void setFinishedExecuting(boolean f) {
        this.finishedExecuting = f;
    }

    public boolean getFinishedExecuting() {
        return this.finishedExecuting;
    }

    public Opcode getOpcode() {
        return this.opcode;
    }

    public Integer getNumCycles() {
        return numCycles;
    }

    public abstract List<Integer> registerOperands();

    public abstract void setDestinationValid(RegisterFile registerFile, boolean valid);

    public abstract void setOperands(RegisterFile registerFile);

    public abstract void execute(Processor processor);

    public abstract void  writeBack(Processor processor);

    public abstract String toString();
}
