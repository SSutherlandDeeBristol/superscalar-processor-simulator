package core.Instructions;

import core.Processor;

public class SubInstruction extends ALUInstruction {

    public SubInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.SUB, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA - this.operandValB;
    }
}
