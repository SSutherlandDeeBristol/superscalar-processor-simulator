package core.instructions;

import core.RegisterFile;

public class JumpInstruction extends Instruction {

    protected Integer jumpTo;

    public JumpInstruction(Integer jumpTo) {
        super(Opcode.JMP, 0);
        this.jumpTo = jumpTo;
    }

    @Override
    public void execute() {

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
