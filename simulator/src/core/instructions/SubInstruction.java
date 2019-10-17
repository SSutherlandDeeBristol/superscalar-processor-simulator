package core.instructions;

public class SubInstruction extends ALUInstruction {

    public SubInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.SUB, destinationRegister, sourceRegisterA, sourceRegisterB, 0);
    }

    @Override
    public void execute() {
        this.result = this.operandValA - this.operandValB;
    }
}
