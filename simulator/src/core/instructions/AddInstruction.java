package core.instructions;

public class AddInstruction extends ALUInstruction {

    public AddInstruction(Integer destinationRegister, Integer sourceRegisterA, Integer sourceRegisterB) {
        super(Opcode.ADD, destinationRegister, sourceRegisterA, sourceRegisterB, 0);
    }

    @Override
    public void execute() {
        this.result = this.operandValA + this.operandValB;
    }
}
