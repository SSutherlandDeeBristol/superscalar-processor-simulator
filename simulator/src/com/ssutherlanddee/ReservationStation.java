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
        if (this.instructionBuffer.isEmpty() && this.registerFile.validOperands(i.registerOperands())) {
            dispatchInstruction(i);
        } else {
            this.instructionBuffer.add(this.instructionBuffer.size(), i);
        }
        this.reorderBuffer.bufferInstruction(i);
        i.setDestinationValid(this.registerFile, false);
        if (i instanceof BranchInstruction)
            ((BranchInstruction) i).setPC(this.registerFile.getPC().get());
    }

    public void dispatch() {
        for (Instruction i : this.instructionBuffer) {
            if (this.registerFile.validOperands(i.registerOperands())) {
                dispatchInstruction(i);
                this.instructionBuffer.remove(i);
                break;
            }
        }
    }

    private void dispatchInstruction(Instruction i) {
        i.setOperands(this.registerFile);
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
        this.instructionBuffer.forEach(i -> System.out.println(i.toString()));
    }
}