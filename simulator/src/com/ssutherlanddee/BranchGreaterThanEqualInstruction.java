package com.ssutherlanddee;

public class BranchGreaterThanEqualInstruction extends BranchInstruction {

    public BranchGreaterThanEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.bge, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA >= valueB) {
            Integer pcValue = processor.getPC().get();
            processor.getPC().set(pcValue + this.offset);
        }
    }
}
