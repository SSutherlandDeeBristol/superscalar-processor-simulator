package com.ssutherlanddee;

import java.util.List;

public class ALUnit extends ExecutionUnit {

    public ALUnit(Integer id, RegisterFile registerFile, boolean interactive) {
        super(id, registerFile, interactive);
    }

    @Override
    public void execute(Processor processor) {
        if (this.finishedInstruction)
            this.currentInstruction = null;

        // if we can pull in a new instruction from the buffer
        if (this.finishedInstruction && !this.instructionBuffer.isEmpty()) {

            // bring in the next instruction from the buffer
            this.currentInstruction = this.instructionBuffer.remove(0);

            // set the delay counter to simulate latency e.g load/store instructions
            this.cycleCounter = this.currentInstruction.getNumCycles();

            //execute the instruction
            this.currentInstruction.execute(processor);

            this.numInstructionsExecuted++;

            if (this.interactive)
                System.out.println("ALU EXECUTED: " + this.currentInstruction.toString());

            this.finishedInstruction = false;
        }

        if (this.cycleCounter > 0)
            this.cycleCounter--;

        if (this.cycleCounter < 1 && !this.finishedInstruction) {
            this.currentInstruction.setFinishedExecuting(true);
            this.finishedInstruction = true;
        }
    }
}
