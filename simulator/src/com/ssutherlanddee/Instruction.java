package com.ssutherlanddee;

import java.util.List;

import com.ssutherlanddee.Operand.OperandType;

public abstract class Instruction {
    public enum State {WAITING, EXECUTING, FINISHED}
    public enum Opcode {nop, mov, cmp, bne, beq, bgt, blt, bge, ble, jmp, add, addi, sub, subi, mul, div, ld, ldi, ldx, st, sti, stx}

    private Opcode opcode;
    private Integer delay;

    protected State state;

    protected Integer tag;

    protected Operand[] operands;

    protected String stringRepresentation;

    public Instruction(Opcode opcode, Integer delay, Integer tag, Operand[] operands) {
        this.opcode = opcode;
        this.delay = delay;
        this.tag = tag;

        this.operands = operands;

        this.state = State.WAITING;

        this.stringRepresentation = String.format(this.getOpcode() + " %s %s %s",
            operands[0].toString(), operands[1].toString(), operands[2].toString());
    }

    public void setFinishedExecuting() {
        this.state = State.FINISHED;
    }

    public boolean getFinishedExecuting() {
        return this.state == State.FINISHED;
    }

    public Opcode getOpcode() {
        return this.opcode;
    }

    public Integer getDelay() {
        return delay;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getTag() {
        return this.tag;
    }

    public Integer getWriteRegister() {
        return operands[0].getContents();
    }

    public Operand updateRegisterOperand(Operand o, RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        Operand operand = registerFile.getRegister(o.getContents()).poll();
        if (operand.getType() == OperandType.ROB)
            return reorderBuffer.poll(operand.getContents());
        return operand;
    }

    public abstract void broadcastTag(Integer tag, Integer value);

    public abstract void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc);

    public abstract boolean ready(RegisterFile registerFile, ReorderBuffer reorderBuffer);

    public abstract void blockDestination(RegisterFile registerFile);

    public abstract void freeDestination(RegisterFile registerFile);

    public abstract void execute(Processor processor);

    public abstract void  writeBack(Processor processor);

    public abstract Integer getResult();

    public abstract String getSourceOperandStatus();

    public String toString() {
        return this.stringRepresentation.concat(" | " + this.state + " | tag: " + this.tag + " | " + getSourceOperandStatus());
    }
}
