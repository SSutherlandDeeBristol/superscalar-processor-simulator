package com.ssutherlanddee;

import java.util.ArrayDeque;
import java.util.Iterator;

import com.ssutherlanddee.Operand.OperandType;

public class ReorderBuffer {

    protected ArrayDeque<Instruction> buffer;
    protected RegisterFile registerFile;
    protected Memory memory;

    protected Integer numInstructionsCompleted;
    private Integer numBranchInstructionsCompleted;

    private Integer capacity;
    private Integer width;

    public ReorderBuffer(RegisterFile registerFile, Memory memory, Integer capacity, Integer width) {
        this.buffer = new ArrayDeque<>();
        this.registerFile = registerFile;
        this.memory = memory;
        this.numInstructionsCompleted = 0;
        this.numBranchInstructionsCompleted = 0;
        this.capacity = capacity;
        this.width = width;
    }

    public void bufferInstruction(Instruction instruction) {
        this.buffer.addLast(instruction);
    }

    public void retire(boolean interactive, Processor processor) {
        if (this.buffer.size() < 1)
            return;

        int numRetired = 0;

        while(this.buffer.size() > 0 && this.buffer.peekFirst().getFinishedExecuting() && numRetired < this.width) {
            Instruction i = this.buffer.removeFirst();

            processor.getTagManager().freeTag(i.getTag());
            i.writeBack(processor);
            i.freeDestination(this.registerFile);

            if (i instanceof BranchInstruction)
                numBranchInstructionsCompleted++;

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

    public Boolean pollForStoreAddress(Integer tag, Integer address) {
        for (Instruction i : this.buffer) {
            if (i.getTag() == tag)
                return false;
            if (i instanceof StoreInstruction) {
                if (!((StoreInstruction) i).getStoreAddress().isPresent()) {
                    return true;
                } else {
                    if (address == ((StoreInstruction) i).getStoreAddress().get()) {
                        return true;
                    }
                }
            } else if (i instanceof StoreImmediateInstruction) {
                if (!((StoreImmediateInstruction) i).getStoreAddress().isPresent()) {
                    return true;
                } else {
                    if (address == ((StoreImmediateInstruction) i).getStoreAddress().get()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Integer numInstructionsCompleted() {
        return this.numInstructionsCompleted;
    }

    public Integer getNumBranchInstructionsCompleted() {
        return this.numBranchInstructionsCompleted;
    }

    public boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    public void flush() {
        this.buffer.clear();
    }

    public Integer getSize() {
        return this.buffer.size();
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void printContents() {
        if (this.buffer.isEmpty()) {
            System.out.println("EMPTY");
        }

        Iterator<Instruction> it = this.buffer.descendingIterator();
        it.forEachRemaining(i -> System.out.println(i.toString()));
    }
}
