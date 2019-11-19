package com.ssutherlanddee;

import java.util.ArrayDeque;

public class ReorderBuffer {

    protected ArrayDeque<Instruction> buffer;
    protected RegisterFile registerFile;
    protected Memory memory;
    protected Integer retiredPC;

    public ReorderBuffer(RegisterFile registerFile, Memory memory) {
        this.buffer = new ArrayDeque<>();
        this.registerFile = registerFile;
        this.memory = memory;
    }

    public void bufferInstruction(Instruction instruction) {
        this.buffer.addFirst(instruction);
    }

    public void retire(boolean interactive, Processor processor) {
        if (this.buffer.size() < 1)
            return;

        while(this.buffer.size() > 0 && this.buffer.peekLast().getFinishedExecuting()) {
            Instruction i = this.buffer.removeLast();
            i.writeBack(processor);
            i.setDestinationValid(this.registerFile, true);

            if (interactive)
                System.out.println("WROTE BACK: " + i.toString());
        }
    }

    public boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    public void flush() {
        this.buffer.clear();
    }

    public void printContents() {
        this.buffer.forEach(i -> System.out.println(i.toString()));
    }
}