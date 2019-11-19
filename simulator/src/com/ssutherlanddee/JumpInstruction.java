package com.ssutherlanddee;

import java.util.Collections;
import java.util.List;

import com.ssutherlanddee.Instruction.Opcode;

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
        this.state = State.EXECUTING;
    }

    @Override
    public List<Integer> registerOperands() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return Opcode.jmp + " " + this.jumpTo + " | " + this.state;
    }
}
