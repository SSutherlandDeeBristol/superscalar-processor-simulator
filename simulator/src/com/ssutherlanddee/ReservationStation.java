package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

public class ReservationStation {

    private Integer id;
    private List<Instruction> instructionBuffer;
    private ExecutionUnit executionUnit;
    private RegisterFile registerFile;

    public ReservationStation(Integer id, ExecutionUnit executionUnit, RegisterFile registerFile) {
        this.id = id;
        this.instructionBuffer = new ArrayList<>();
        this.executionUnit = executionUnit;
        this.registerFile = registerFile;
    }

    public void issue(Instruction i) {
        if (this.instructionBuffer.isEmpty() && this.registerFile.validOperands(i.registerOperands())) {
            dispatchInstruction(i);
        } else {
            instructionBuffer.add(i);
        }
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
        i.setDestinationValid(this.registerFile, false);
        this.executionUnit.bufferInstruction(i);
    }

    public Integer getBufferSize() {
        return this.instructionBuffer.size();
    }

    public Integer getId() {
        return this.id;
    }
}