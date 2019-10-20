package core.instructions;

import core.Processor;
import core.RegisterFile;

public class LoadInstruction extends LoadStoreInstruction {

    private Integer destinationRegister;
    private Integer sourceRegister;
    private Integer offset;

    public LoadInstruction(Integer destinationRegister, Integer sourceRegister, Integer offset) {
        super(Opcode.LD, 1);
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
        this.offset = offset;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        Integer sourceRegisterValue = (Integer) processor.getRegisterFile().getRegister(this.sourceRegister).get();
        Integer toLoad = (Integer) processor.getMemory().getMemoryByAddress(sourceRegisterValue + this.offset);
        processor.getRegisterFile().getRegister(this.destinationRegister).set(toLoad);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return Opcode.LD + " r" + this.destinationRegister + " r" + this.sourceRegister + " " + this.offset;
    }
}
