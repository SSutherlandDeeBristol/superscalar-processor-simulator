package com.ssutherlanddee;

public class MoveInstruction extends LoadStoreInstruction {

    public MoveInstruction(Operand[] operands, Integer tag) {
        super(Opcode.mov, 1, tag, operands);
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destination.getContents()).set(this.sourceA.getContents());
        this.state = State.FINISHED;
    }
}
