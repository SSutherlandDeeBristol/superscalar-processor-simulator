package core.Instructions;

import core.Processor;

public class BranchLessThanInstruction extends BranchInstruction {

    public BranchLessThanInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.BLT, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA < valueB) {
            Integer pcValue = processor.getPC().get();
            processor.getPC().set(pcValue + this.offset);
        }
    }
}
