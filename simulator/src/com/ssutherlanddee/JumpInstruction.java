package com.ssutherlanddee;

import java.util.Collections;
import java.util.List;

public class JumpInstruction extends BranchInstruction {

    private Integer jumpTo;

    public JumpInstruction(Integer jumpTo) {
        super(Opcode.jmp, 1, -1, -1, 0);
        this.jumpTo = jumpTo;
    }

    @Override
    public void execute(Processor processor) {
        this.shouldBranch = true;
        this.branchTo = jumpTo;
    }

    @Override
    public List<Integer> registerOperands() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "jmp " + this.jumpTo;
    }
}
