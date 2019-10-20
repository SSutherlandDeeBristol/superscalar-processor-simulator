package core.instructions;

import core.Processor;
import core.RegisterFile;

public class JumpInstruction extends BranchInstruction {

    protected Integer jumpTo;

    public JumpInstruction(Integer jumpTo) {
        super(Opcode.JMP, 1);
        this.jumpTo = jumpTo;
    }

    @Override
    public void execute(Processor processor) {
        processor.getPC().set(this.jumpTo);
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return "JMP " + this.jumpTo;
    }
}
