package core.instructions;

import core.Processor;
import core.Register;
import core.RegisterFile;

public abstract class ALUInstruction extends Instruction {

    protected Integer destinationRegister;
    protected Integer sourceRegisterA;
    protected Integer sourceRegisterB;
    protected Integer result;
    protected Integer operandValA;
    protected Integer operandValB;

    public ALUInstruction(Opcode opcode, Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB,
                          Integer delay) {
        super(opcode, delay);

        this.destinationRegister = destinationRegister;
        this.sourceRegisterA = sourceRegisterA;
        this.sourceRegisterB = sourceRegisterB;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {
        this.operandValA = (Integer) registerFile.getRegister(sourceRegisterA).get();
        this.operandValB = (Integer) registerFile.getRegister(sourceRegisterB).get();
    }

    @Override
    public void execute(Processor processor) {

    }

    @Override
    public void writeBack(RegisterFile registerFile) {
        registerFile.getRegister(this.destinationRegister).set(this.result);
    }

    @Override
    public String toString() {
        return String.format(this.getOpcode() + " r%d r%d r%d", this.destinationRegister, this.sourceRegisterA, this.sourceRegisterB);
    }
}
