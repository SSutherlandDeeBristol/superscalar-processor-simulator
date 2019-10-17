package core.instructions;

import core.RegisterFile;

public abstract class Instruction {
    public enum Opcode {MOV, CMP, JMP, ADD, SUB, LD, ST}

    private Opcode opcode;
    private Integer delay;

    public Instruction(Opcode opcode, Integer delay) {
        this.opcode = opcode;
        this.delay = delay;
    }

    public Opcode getOpcode() {
        return this.opcode;
    }

    public Integer getDelay() {
        return delay;
    }

    public abstract void setOperands(RegisterFile registerFile);

    public abstract void execute();

    public abstract void  writeBack(RegisterFile registerFile);

    public abstract String toString();
}
