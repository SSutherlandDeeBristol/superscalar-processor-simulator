package core.instructions;

import core.Processor;
import core.RegisterFile;

public class MoveInstruction extends LoadStoreInstruction {

    private Integer destinationRegister;
    private Integer sourceRegister;

    public MoveInstruction(Integer destinationRegister, Integer sourceRegister) {
        super(Opcode.MOV, 1);
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        Integer toMoveValue = (Integer) processor.getRegisterFile().getRegister(this.sourceRegister).get();
        processor.getRegisterFile().getRegister(this.destinationRegister).set(toMoveValue);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return Opcode.MOV + " r" + this.destinationRegister + " r" + this.sourceRegister;
    }
}
