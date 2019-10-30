package com.ssutherlanddee;

public abstract class Instruction {
    public enum Opcode {mov, cmp, bne, beq, bgt, blt, bge, ble, jmp, add, addi, sub, subi, mul, div, ld, ldi, st, sti}

    private Opcode opcode;
    private Integer numCycles;

    public Instruction(Opcode opcode, Integer delay) {
        this.opcode = opcode;
        this.numCycles = delay;
    }

    public Opcode getOpcode() {
        return this.opcode;
    }

    public Integer getNumCycles() {
        return numCycles;
    }

    public abstract void setOperands(RegisterFile registerFile);

    public abstract void execute(Processor processor);

    public abstract void  writeBack(RegisterFile registerFile);

    public abstract String toString();
}
