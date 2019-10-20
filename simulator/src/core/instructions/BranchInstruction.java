package core.instructions;

import core.Processor;
import core.RegisterFile;

public class BranchInstruction extends Instruction {

    public BranchInstruction(Opcode opcode, Integer delay) {
        super(opcode, delay);
    }
    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {

    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return null;
    }
}
