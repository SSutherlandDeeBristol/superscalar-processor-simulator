package com.ssutherlanddee;

public class CompareInstruction extends ALUInstruction {

    public CompareInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.cmp, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        this.operandValA = registerFile.getRegister(this.sourceRegisterA).get();
        this.operandValB = registerFile.getRegister(this.sourceRegisterB).get();
    }

    @Override
    public void execute(Processor processor) {
        if (this.operandValA < this.operandValB) {
            this.result = -1;
        } else if (this.operandValA > this.operandValB) {
            this.result = 1;
        }
        this.state = State.EXECUTING;
    }

    @Override
    public void writeBack(Processor processor) {
        processor.getRegisterFile().getRegister(this.destinationRegister).set(this.result);
        this.state = State.FINISHED;
    }

    @Override
    public String toString() {
        return Opcode.cmp + " r" + this.destinationRegister + " r" + this.sourceRegisterA + " r" + this.sourceRegisterB + " | " + this.state;
    }
}
