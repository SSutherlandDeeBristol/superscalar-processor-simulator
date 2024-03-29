package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

public abstract class ExecutionUnit {

    protected Integer id;

    protected Integer cycleCounter;

    protected Instruction currentInstruction;

    protected RegisterFile registerFile;

    protected List<Instruction> instructionBuffer;

    protected boolean finishedInstruction;

    protected Integer numInstructionsExecuted;

    protected boolean interactive;

    public ExecutionUnit(Integer id, RegisterFile registerFile, boolean interactive) {
        this.id = id;
        this.cycleCounter = 0;
        this.currentInstruction = null;
        this.registerFile = registerFile;
        this.instructionBuffer = new ArrayList<>();
        this.finishedInstruction = true;
        this.numInstructionsExecuted = 0;
        this.interactive = interactive;
    }

    public abstract void execute(Processor processor);

    public void flush() {
        this.instructionBuffer.clear();
        this.currentInstruction = null;
        this.finishedInstruction = true;
    }

    public boolean bufferIsEmpty() {
        return this.instructionBuffer.isEmpty();
    }

    public boolean bufferNotEmpty() {
        return !bufferIsEmpty();
    }

    public Integer getBufferSize() {
        return this.instructionBuffer.size();
    }

    public void bufferInstruction(Instruction instruction) {
        this.instructionBuffer.add(instruction);
    }

    public boolean isExecuting() {
        return !this.finishedInstruction;
    }

    public boolean isIdle() {
        return !isExecuting();
    }

    public String getStatus() {
        if (this.currentInstruction != null) {
            return this.currentInstruction.toString() + " | counter: " + this.cycleCounter;
        } else {
            return "No Instruction Executing";
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumInstructionsExecuted() {
        return this.numInstructionsExecuted;
    }
}
