package com.ssutherlanddee;

public abstract class BranchInstruction extends Instruction {

    protected Integer valueA;
    protected Integer valueB;
    protected Integer sourceRegisterA;
    protected Integer sourceRegisterB;
    protected Integer offset;

    public BranchInstruction(Opcode opcode, Integer delay, Integer sourceRegisterA,
                             Integer sourceRegisterB, Integer offset) {
        super(opcode, delay);
        this.sourceRegisterA = sourceRegisterA;
        this.sourceRegisterB = sourceRegisterB;
        this.offset = offset;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        this.valueA = registerFile.getRegister(this.sourceRegisterA).get();
        this.valueB = registerFile.getRegister(this.sourceRegisterB).get();
    }

    @Override
    public void execute(Processor processor) {
    }

    @Override
    public void writeBack(RegisterFile registerFile) {
    }

    @Override
    public String toString() {
        return this.getOpcode() + " r" + this.sourceRegisterA + " r" + this.sourceRegisterB + " " + this.offset;
    }
}
