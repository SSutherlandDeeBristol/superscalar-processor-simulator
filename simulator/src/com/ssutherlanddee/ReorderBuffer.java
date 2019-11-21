package com.ssutherlanddee;

import java.util.ArrayDeque;

import com.ssutherlanddee.Operand.OperandType;

public class ReorderBuffer {

    protected ArrayDeque<Instruction> buffer;
    protected RegisterFile registerFile;
    protected Memory memory;

    protected Integer numInstructionsCompleted;

    public ReorderBuffer(RegisterFile registerFile, Memory memory) {
        this.buffer = new ArrayDeque<>();
        this.registerFile = registerFile;
        this.memory = memory;
        this.numInstructionsCompleted = 0;
    }

    public void bufferInstruction(Instruction instruction) {
        this.buffer.addFirst(instruction);
    }

    public void retire(boolean interactive, Processor processor) {
        if (this.buffer.size() < 1)
            return;

        int numRetired = 0;

        while(this.buffer.size() > 0 && this.buffer.peekLast().getFinishedExecuting() && numRetired < 4) {
            Instruction i = this.buffer.removeLast();

            processor.getTagManager().freeTag(i.getTag());
            i.freeDestination(this.registerFile);
            i.writeBack(processor);

            numInstructionsCompleted++;
            numRetired++;

            if (interactive)
                System.out.println("WROTE BACK: " + i.toString());
        }
    }

    public Operand poll(Integer tag) {
        for (Instruction i : this.buffer) {
            if (i.getTag() == tag && i.getFinishedExecuting())
                return new Operand(OperandType.VALUE, i.getResult());
        }
        return new Operand(OperandType.TAG, tag);
    }

    public Integer numInstructionsCompleted() {
        return this.numInstructionsCompleted;
    }

    public boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    public void flush() {
        this.buffer.clear();
    }

    public void printContents() {
        if (this.buffer.isEmpty()) {
            System.out.println("EMPTY");
        }
        this.buffer.forEach(i -> System.out.println(i.toString()));
    }
}