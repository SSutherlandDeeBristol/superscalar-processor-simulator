package core.Instructions;

import core.Processor;
import core.RegisterFile;

public abstract class Instruction {
    public enum Opcode {MOV, CMP, BNE, BEQ, BGT, BLT, BGE, BLE, JMP, ADD, SUB, MUL, DIV, LD, LDI, ST}

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
