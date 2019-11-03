package com.ssutherlanddee;

import java.util.Arrays;
import java.util.List;

public class LoadStoreInstruction extends Instruction {

    protected Integer destinationRegister;
    protected Integer sourceRegister;
    protected Integer sourceValue;

    public LoadStoreInstruction(Opcode opcode, Integer delay, Integer destinationRegister, Integer sourceRegister) {
        super(opcode, delay);
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        if (this.sourceRegister != -1)
            sourceValue = registerFile.getRegister(this.sourceRegister).get();
    }

    @Override
    public List<Integer> registerOperands() {
        return Arrays.asList(destinationRegister, sourceRegister);
    }

    @Override
    public void setDestinationValid(RegisterFile registerFile, boolean valid) {
        registerFile.getRegister(this.destinationRegister).setValid(valid);
    }

    @Override
    public void execute(Processor processor) {}

    @Override
    public void writeBack(RegisterFile registerFile) {}

    @Override
    public String toString() {
        return null;
    }
}
