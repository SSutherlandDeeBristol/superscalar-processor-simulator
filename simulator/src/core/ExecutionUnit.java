package core;

import core.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public abstract class ExecutionUnit {

    protected Integer id;

    protected Integer cycleCounter;

    protected Instruction currentInstruction;

    protected RegisterFile registerFile;

    public ExecutionUnit(Integer id, RegisterFile registerFile) {
        this.id = id;
        this.cycleCounter = 0;
        this.currentInstruction = null;
        this.registerFile = registerFile;
    }

    public abstract void execute(Processor processor);

    public abstract boolean bufferIsEmpty();

    public boolean isExecuting() {
        return !(currentInstruction == null);
    }

    public String getStatus() {
        if (currentInstruction != null) {
            return this.currentInstruction.toString() + " | counter: " + this.cycleCounter + "\n";
        } else {
            return "No instruction executing\n";
        }
    }

    public Integer getId() {
        return id;
    }
}
