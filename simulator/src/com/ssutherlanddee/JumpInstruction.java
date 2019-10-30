package com.ssutherlanddee;

public class JumpInstruction extends BranchInstruction {

    private Integer jumpTo;

    public JumpInstruction(Integer jumpTo) {
        super(Opcode.jmp, 1, 0, 0, 0);
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
        return "jmp " + this.jumpTo;
    }
}
