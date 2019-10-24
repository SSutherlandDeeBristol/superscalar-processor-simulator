package core.Instructions;

import core.Processor;

public class DivisionInstruction extends ALUInstruction {

    public DivisionInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.DIV, destinationRegister, sourceRegisterA, sourceRegisterB, 1);
    }

    @Override
    public void execute(Processor processor) {
        this.result = this.operandValA / this.operandValB;
    }
}
