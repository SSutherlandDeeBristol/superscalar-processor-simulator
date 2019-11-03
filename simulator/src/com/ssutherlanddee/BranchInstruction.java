package com.ssutherlanddee;

import java.util.Arrays;
import java.util.List;

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
    public List<Integer> registerOperands() {
        return Arrays.asList(sourceRegisterA, sourceRegisterB);
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        if (this.sourceRegisterA != -1)
            this.valueA = registerFile.getRegister(this.sourceRegisterA).get();

        if (this.sourceRegisterB != -1)
            this.valueB = registerFile.getRegister(this.sourceRegisterB).get();
    }

    @Override
    public void setDestinationValid(RegisterFile registerFile, boolean valid) {}

    @Override
    public void execute(Processor processor) {}

    @Override
    public void writeBack(RegisterFile registerFile) {}

    @Override
    public String toString() {
        return this.getOpcode() + " r" + this.sourceRegisterA + " r" + this.sourceRegisterB + " " + this.offset;
    }
}
