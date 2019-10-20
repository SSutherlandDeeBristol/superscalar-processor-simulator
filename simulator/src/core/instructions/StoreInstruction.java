package core.instructions;

import core.Processor;
import core.RegisterFile;

public class StoreInstruction extends LoadStoreInstruction {

    private Integer destinationRegister;
    private Integer sourceRegister;
    private Integer offset;

    public StoreInstruction(Integer sourceRegister, Integer destinationRegister, Integer offset) {
        super(Instruction.Opcode.ST, 1);
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
        this.offset = offset;
    }

    @Override
    public void setOperands(RegisterFile registerFile) {

    }

    @Override
    public void execute(Processor processor) {
        Integer toStore = (Integer) processor.getRegisterFile().getRegister(this.sourceRegister).get();
        Integer baseAddress = (Integer) processor.getRegisterFile().getRegister(this.destinationRegister).get();
        processor.getMemory().setMemoryByAddress(baseAddress + this.offset, toStore);
    }

    @Override
    public void writeBack(RegisterFile registerFile) {

    }

    @Override
    public String toString() {
        return Instruction.Opcode.ST + " r" + this.destinationRegister + " r" + this.sourceRegister + " " + this.offset;
    }

}
