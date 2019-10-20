package core.instructions;

import core.Processor;
import core.RegisterFile;

public class CompareInstruction extends ALUInstruction {

    private Integer destinationRegister;
    private Integer sourceRegisterA;
    private Integer sourceRegisterB;

    public CompareInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.CMP, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
        this.destinationRegister = destinationRegister;
        this.sourceRegisterA = sourceRegisterA;
        this.sourceRegisterB = sourceRegisterB;
    }
    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        Integer valueA = (Integer) processor.getRegisterFile().getRegister(this.sourceRegisterA).get();
        Integer valueB = (Integer) processor.getRegisterFile().getRegister(this.sourceRegisterB).get();
        Integer toWrite = 0;
        if (valueA < valueB) {
            toWrite = -1;
        } else if (valueA > valueB) {
            toWrite = 1;
        }

        processor.getRegisterFile().getRegister(this.destinationRegister).set(toWrite);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return Opcode.CMP + " r" + this.destinationRegister + " r" + this.sourceRegisterA + " r" + this.sourceRegisterB;
    }
}
