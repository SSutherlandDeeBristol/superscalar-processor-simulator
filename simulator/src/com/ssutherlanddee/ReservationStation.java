package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

public class ReservationStation {

    private Integer id;
    private List<Instruction> instructionBuffer;
    private ExecutionUnit executionUnit;
    private RegisterFile registerFile;
    private ReorderBuffer reorderBuffer;

    public ReservationStation(Integer id, ExecutionUnit executionUnit, RegisterFile registerFile, ReorderBuffer reorderBuffer) {
        this.id = id;
        this.instructionBuffer = new ArrayList<>();
        this.executionUnit = executionUnit;
        this.registerFile = registerFile;
        this.reorderBuffer = reorderBuffer;
    }

    public void issue(Instruction i) {
        // Set the operands to tags or values
        i.updateOperands(this.registerFile, this.reorderBuffer);
        // Block the destination register
        i.blockDestination(this.registerFile);
        // Add the instruciton to the reorder buffer
        this.reorderBuffer.bufferInstruction(i);

        // If the instruction is ready and can be bypassed then dispatch straight away
        if (this.instructionBuffer.isEmpty() && i.ready(this.registerFile)) {
            dispatchInstruction(i);
        } else {
            this.instructionBuffer.add(this.instructionBuffer.size(), i);
        }

        if (i instanceof BranchInstruction)
            ((BranchInstruction) i).setPC(this.registerFile.getPC().get());
    }

    public void dispatch() {
        for (Instruction i : this.instructionBuffer) {
            // If the instruction is ready to be dispatched then dispatch it
            if (i.ready(this.registerFile)) {
                dispatchInstruction(i);
                this.instructionBuffer.remove(i);
                break;
            }
        }
    }

    public void broadcast(Integer tag, Integer value) {
        for (Integer i = 0; i < this.instructionBuffer.size(); i++) {
            this.instructionBuffer.get(i).broadcastTag(tag, value);
        }
    }

    private void dispatchInstruction(Instruction i) {
        this.executionUnit.bufferInstruction(i);
    }

    public void flush() {
        this.instructionBuffer.clear();
    }

    public Integer getBufferSize() {
        return this.instructionBuffer.size();
    }

    public boolean bufferIsEmpty() {
        return this.instructionBuffer.isEmpty();
    }

    public boolean bufferNotEmpty() {
        return !bufferIsEmpty();
    }

    public ExecutionUnit getExecutionUnit() {
        return this.executionUnit;
    }

    public Integer getId() {
        return this.id;
    }

    public void printContents() {
        if (this.instructionBuffer.isEmpty()) {
            System.out.println(String.format("ID %d | EMPTY", this.id));
        }
        this.instructionBuffer.forEach(i -> System.out.println(String.format("ID %d | %s", this.id, i.toString())));
    }
}