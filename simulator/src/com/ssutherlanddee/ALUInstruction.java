package com.ssutherlanddee;

import java.util.List;
import java.util.Arrays;

public abstract class ALUInstruction extends Instruction {

    protected Integer destinationRegister;
    protected Integer sourceRegisterA;
    protected Integer sourceRegisterB;
    protected Integer result;
    protected Integer operandValA;
    protected Integer operandValB;

    public ALUInstruction(Opcode opcode, Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB,
                          Integer delay) {
        super(opcode, delay);

        this.destinationRegister = destinationRegister;
        this.sourceRegisterA = sourceRegisterA;
        this.sourceRegisterB = sourceRegisterB;
    }

    @Override
    public List<Integer> registerOperands() {
        return Arrays.asList(sourceRegisterA, sourceRegisterB);
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        if (this.sourceRegisterA != -1)
            this.operandValA = registerFile.getRegister(sourceRegisterA).get();
        if (this.sourceRegisterB != -1)
            this.operandValB = registerFile.getRegister(sourceRegisterB).get();
    }

    @Override
    public void setDestinationValid(RegisterFile registerFile, boolean valid) {
        registerFile.getRegister(this.destinationRegister).setValid(valid);
    }

    @Override
    public void execute(Processor processor) {}

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(this.result);
    }

    @Override
    public String toString() {
        return String.format(this.getOpcode() + " r%d r%d r%d", this.destinationRegister, this.sourceRegisterA, this.sourceRegisterB);
    }
}
