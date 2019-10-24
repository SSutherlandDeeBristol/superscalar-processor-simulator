package core.Instructions;

import core.Processor;

public class BranchGreaterThanEqualInstruction extends BranchInstruction {

    public BranchGreaterThanEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.BGE, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (valueA >= valueB) {
            Integer pcValue = processor.getPC().get();
            processor.getPC().set(pcValue + this.offset);
        }
    }
}
