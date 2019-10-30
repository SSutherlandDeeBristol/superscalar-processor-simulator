package com.ssutherlanddee;

public class MoveInstruction extends LoadStoreInstruction {

    private Integer destinationRegister;
    private Integer sourceRegister;
    private Integer moveValue;

    public MoveInstruction(Integer destinationRegister, Integer sourceRegister) {
        super(Opcode.mov, 1);
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
        this.moveValue = 0;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        this.moveValue = processor.getRegisterFile().getRegister(this.sourceRegister).get();
    }

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(moveValue);
    }

    @Override
    public String toString() {
        return Opcode.mov + " r" + this.destinationRegister + " r" + this.sourceRegister;
    }
}
