package core;

import core.instructions.Instruction;

import java.util.LinkedList;

public class ExecutionUnit {

    private Integer id;

    private Integer delayCounter;

    private Instruction currentInstruction;

    private LinkedList<Instruction> instructionBuffer = new LinkedList<>();

    private RegisterFile registerFile;

    public ExecutionUnit(Integer id, RegisterFile registerFile) {
        this.id = id;
        this.delayCounter = 0;
        this.currentInstruction = null;
        this.registerFile = registerFile;
    }

    public void execute() {
        if (delayCounter < 1 && currentInstruction != null) {
            currentInstruction.writeBack(registerFile);
            currentInstruction = null;
        } else {
            delayCounter--;
        }

        if (currentInstruction == null && !instructionBuffer.isEmpty() && delayCounter <= 0) {
            // bring in the next instruction from the buffer
            currentInstruction = instructionBuffer.removeFirst();
            // set the delay counter to simulate latency e.g load/store instructions
            if (currentInstruction.getDelay() > 0)
                delayCounter = currentInstruction.getDelay();

            currentInstruction.setOperands(registerFile);
            currentInstruction.execute();
        }

        System.out.println(registerFile.toString());

        return;
    }

    public void bufferInstruction(Instruction i) {
        this.instructionBuffer.add(i);
    }

    public boolean bufferIsEmpty() {
        return this.instructionBuffer.isEmpty();
    }

    public boolean isExecuting() {
        return !(currentInstruction == null);
    }

    public String getStatus() {
        if (currentInstruction != null) {
            return this.currentInstruction.toString() + " | counter: " + this.delayCounter + "\n";
        } else {
            return "No instruction executing\n";
        }
    }

    public Integer getId() {
        return id;
    }
}
