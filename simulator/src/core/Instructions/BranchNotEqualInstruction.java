package core.Instructions;

import core.Processor;
import core.RegisterFile;

public class BranchNotEqualInstruction extends BranchInstruction {

    public BranchNotEqualInstruction(Integer sourceRegisterA, Integer sourceRegisterB, Integer offset) {
        super(Opcode.BNE, 1, sourceRegisterA, sourceRegisterB, offset);
    }

    @Override
    public void execute(Processor processor) {
        if (!valueB.equals(valueA)) {
            Integer pcValue = processor.getPC().get();
            processor.getPC().set(pcValue + this.offset);
        }
    }
}
