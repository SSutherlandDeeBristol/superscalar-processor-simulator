package core;

import core.instructions.ALUInstruction;
import core.instructions.Instruction;

import java.util.Collections;
import java.util.List;

public class ALUnit extends ExecutionUnit {

    private List<ALUInstruction> instructionBuffer;

    public ALUnit(Integer id, RegisterFile registerFile, List<ALUInstruction> instructionBuffer) {
        super(id, registerFile);
        this.instructionBuffer = instructionBuffer;
    }

    @Override
    public void execute(Processor processor) {
        if (this.cycleCounter < 1 && this.currentInstruction != null) {
            this.currentInstruction.writeBack(this.registerFile);
            currentInstruction = null;
        }

        // if we can pull in a new instruction from the buffer
        if (this.currentInstruction == null && !this.instructionBuffer.isEmpty()) {

            // bring in the next instruction from the buffer
            this.currentInstruction = this.instructionBuffer.remove(0);

            // set the delay counter to simulate latency e.g load/store instructions
            this.cycleCounter = this.currentInstruction.getNumCycles();

            // set the values of the operands at this point
            this.currentInstruction.setOperands(registerFile);

            //execute the instruction
            this.currentInstruction.execute(processor);
        }

        cycleCounter--;
    }

    @Override
    public boolean bufferIsEmpty() {
        return this.instructionBuffer.isEmpty();
    }

}
