package com.ssutherlanddee;

public class BranchNotEqualInstruction extends BranchInstruction {

    public BranchNotEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.bne, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (!valueB.equals(valueA)) {
            Integer pcValue = processor.getPC().get();
            processor.getPC().set(pcValue + this.offset);
        }
    }
}
