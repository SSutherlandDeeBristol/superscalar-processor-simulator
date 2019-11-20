package com.ssutherlanddee;

import java.util.List;

import com.ssutherlanddee.Instruction.State;

public class LoadStoreUnit extends ExecutionUnit {

    public LoadStoreUnit(Integer id, RegisterFile registerFile, boolean interactive) {
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
            this.cycleCounter = this.currentInstruction.getDelay();

            //execute the instruction
            this.currentInstruction.execute(processor);

            this.numInstructionsExecuted++;

            if (this.interactive)
                System.out.println("LOAD/STORE EXECUTED: " + this.currentInstruction.toString());

            this.finishedInstruction = false;
        }

        if (this.cycleCounter > 0)
            this.cycleCounter--;

        if (this.cycleCounter < 1 && !this.finishedInstruction) {
            this.currentInstruction.setFinishedExecuting();
            if (this.currentInstruction instanceof LoadImmediateInstruction ||
                this.currentInstruction instanceof LoadInstruction) {
                    processor.broadcast(this.currentInstruction.getWriteRegister(), this.currentInstruction.getTag(), this.currentInstruction.getResult());
                }
            this.finishedInstruction = true;
        }
    }
}
