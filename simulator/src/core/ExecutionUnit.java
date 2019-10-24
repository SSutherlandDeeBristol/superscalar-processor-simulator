package core;

import core.Instructions.Instruction;

import java.util.List;

public abstract class ExecutionUnit {

    protected Integer id;

    protected Integer cycleCounter;

    protected Instruction currentInstruction;

    protected RegisterFile registerFile;

    protected List<Instruction> toWriteBack;

    protected boolean finishedInstruction;

    public ExecutionUnit(Integer id, RegisterFile registerFile, List<Instruction> toWriteBack) {
        this.id = id;
        this.cycleCounter = 0;
        this.currentInstruction = null;
        this.registerFile = registerFile;
        this.toWriteBack = toWriteBack;
        this.finishedInstruction = true;
    }

    public abstract void execute(Processor processor);

    public abstract boolean bufferIsEmpty();

    public boolean isExecuting() {
        return !this.finishedInstruction;
    }

    public String getStatus() {
        if (!this.finishedInstruction) {
            return this.currentInstruction.toString() + " | counter: " + this.cycleCounter + "\n";
        } else {
            return "No instruction executing\n";
        }
    }

    public Integer getId() {
        return id;
    }
}
