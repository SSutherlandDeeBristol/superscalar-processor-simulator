package com.ssutherlanddee;

import com.ssutherlanddee.Operand.OperandType;

public abstract class BranchInstruction extends Instruction {

    protected Operand sourceA;
    protected Operand sourceB;
    protected Operand offset;

    protected Integer PC;

    protected boolean shouldBranch;
    protected Integer branchTo;

    public BranchInstruction(Opcode opcode, Integer delay, Integer tag, Operand[] operands) {
        super(opcode, delay, tag, operands);
        this.sourceA = operands[0];
        this.sourceB = operands[1];
        this.offset = operands[2];

        this.PC = 0;
        this.shouldBranch = false;
        this.branchTo = 0;
    }

    @Override
    public void broadcastTag(Integer tag, Integer value) {
        if (this.sourceA.getType() == OperandType.TAG && this.sourceA.getContents() == tag)
            this.sourceA.setType(OperandType.VALUE, value);
        if (this.sourceB.getType() == OperandType.TAG && this.sourceB.getContents() == tag)
            this.sourceB.setType(OperandType.VALUE, value);
    }

    @Override
    public void updateOperands(RegisterFile registerFile, ReorderBuffer reorderBuffer, Integer pc) {
        if (this.sourceA.getType() == OperandType.REGISTER)
            this.sourceA = updateRegisterOperand(this.sourceA, registerFile, reorderBuffer);
        if (this.sourceB.getType() == OperandType.REGISTER)
            this.sourceB = updateRegisterOperand(this.sourceB, registerFile, reorderBuffer);
        this.PC = pc;
    }

    @Override
    public boolean ready(RegisterFile registerFile) {
        return (this.sourceA.isReady() && this.sourceB.isReady());
    }

    @Override
    public void blockDestination(RegisterFile registerFile) {
        registerFile.getPC().block(this.tag);
    }

    @Override
    public void freeDestination(RegisterFile registerFile) {
        registerFile.getPC().free();
    }

    @Override
    public void execute(Processor processor) {}

    @Override
    public void writeBack(Processor processor) {
        if (this.shouldBranch) {
            processor.getRegisterFile().getPC().set(this.branchTo);
            processor.flushPipeline();
        }
    }

    @Override
    public Integer getResult() {
        return 0;
    }

    public void setPC(Integer PC) {
        this.PC = PC;
    }
}
