package com.ssutherlanddee;

import java.util.List;

import com.ssutherlanddee.Operand.OperandType;

import java.util.Arrays;

public abstract class ALUInstruction extends Instruction {

    protected Operand destination;
    protected Operand sourceA;
    protected Operand sourceB;

    protected Integer result;

    public ALUInstruction(Opcode opcode, Integer delay, Integer tag, Operand[] operands) {
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
    public List<Operand> getSourceOperands() {
        return Arrays.asList(this.sourceA, this.sourceB);
    }

    @Override
    public void updateOperands(RegisterFile registerFile) {
        if (this.sourceA.getType() == OperandType.REGISTER)
            this.sourceA = registerFile.getRegister(this.sourceA.getContents()).poll();
        if (this.sourceB.getType() == OperandType.REGISTER)
            this.sourceB = registerFile.getRegister(this.sourceB.getContents()).poll();
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
    public void execute(Processor processor) {}

    @Override
    public void writeBack(Processor processor) {
        //processor.getRegisterFile().getRegister(this.destination.getContents()).set(this.result);
        this.state = State.FINISHED;
    }

    @Override
    public Integer getResult() {
        return this.result;
    }
}
