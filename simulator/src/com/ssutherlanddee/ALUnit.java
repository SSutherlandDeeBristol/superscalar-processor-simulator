package com.ssutherlanddee;

import java.util.List;

public class ALUnit extends ExecutionUnit {

    private List<ALUInstruction> instructionBuffer;

    public ALUnit(Integer id, RegisterFile registerFile, List<ALUInstruction> instructionBuffer, List<Instruction> toWriteBack) {
        super(id, registerFile, toWriteBack);
        this.instructionBuffer = instructionBuffer;
    }

    @Override
    public void execute(Processor processor) {
        // if we can pull in a new instruction from the buffer
        if (this.finishedInstruction && !this.instructionBuffer.isEmpty()) {

            // bring in the next instruction from the buffer
            this.currentInstruction = this.instructionBuffer.remove(0);

            // set the delay counter to simulate latency e.g load/store instructions
            this.cycleCounter = this.currentInstruction.getNumCycles();

            // set the values of the operands at this point
            this.currentInstruction.setOperands(this.registerFile);

            //execute the instruction
            this.currentInstruction.execute(processor);

            this.finishedInstruction = false;
        }

        if (this.cycleCounter > 0)
            this.cycleCounter--;

        if (this.cycleCounter < 1 && !this.finishedInstruction) {
            this.toWriteBack.add(this.currentInstruction);
            this.finishedInstruction = true;
        }
    }

    @Override
    public boolean bufferIsEmpty() {
        return this.instructionBuffer.isEmpty();
    }

}
