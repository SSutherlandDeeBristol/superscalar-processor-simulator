package com.ssutherlanddee;

public class BranchGreaterThanInstruction extends BranchInstruction {

    public BranchGreaterThanInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.bgt, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA > valueB) {
            Integer pcValue = processor.getPC().get();
            processor.getPC().set(pcValue + this.offset);
        }
    }
}
