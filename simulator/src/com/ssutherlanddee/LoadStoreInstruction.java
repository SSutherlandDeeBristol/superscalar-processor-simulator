package com.ssutherlanddee;

import java.util.Arrays;
import java.util.List;

public class LoadStoreInstruction extends Instruction {

    protected Integer destinationRegister;
    protected Integer sourceRegisterA;
    protected Integer sourceRegisterB;
    protected Integer sourceValueA;
    protected Integer sourceValueB;

    public LoadStoreInstruction(Opcode opcode, Integer delay, Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(opcode, delay);
        this.destinationRegister = destinationRegister;
        this.sourceRegisterA = sourceRegisterA;
        this.sourceRegisterB = sourceRegisterB;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        if (this.sourceRegisterA != -1)
            this.sourceValueA = registerFile.getRegister(this.sourceRegisterA).get();
        if (this.sourceRegisterB != -1)
            this.sourceValueB = registerFile.getRegister(this.sourceRegisterB).get();
    }

    @Override
    public List<Integer> registerOperands() {
        return Arrays.asList(sourceRegisterA, sourceRegisterB);
    }

    @Override
    public void setDestinationValid(RegisterFile registerFile, boolean valid) {
        if (this.destinationRegister != -1)
            registerFile.getRegister(this.destinationRegister).setValid(valid);
    }

    @Override
    public void execute(Processor processor) {}

    @Override
    public void writeBack(Processor processor) {}

    @Override
    public String toString() {
        return null;
    }
}
