package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memory {

    private HashMap<Integer, Object> mainMemory;

    private List<String> instructionMemory;
    private HashMap<String, Integer> labelMap;

    public Memory() {
        mainMemory = new HashMap<>();
        instructionMemory = new ArrayList<>();
        labelMap = new HashMap<>();
    }

    public void loadProgramIntoMemory(Program program) {
        instructionMemory.addAll(program.getInstructionList());
        labelMap.putAll(program.getLabelMap());
    }

    public String getInstructionByAddress(Integer address) {
        if (address >= this.instructionMemory.size())
            return null;

        return this.instructionMemory.get(address);
    }

    public Object getMemoryByAddress(Integer address) {
        if (!this.mainMemory.containsKey(address))
            return 0;
        return this.mainMemory.get(address);
    }

    public Integer instructionsRemaining(Integer pc) {
        return (instructionMemory.size()) - pc;
    }

    public void setMemoryByAddress(Integer address, Object object) {
        this.mainMemory.put(address, object);
    }

    public void printContents(Integer maxAddress) {
        for (int i = 0; i < maxAddress; ++i) {
            System.out.println(String.format("%3d | %s", i, getMemoryByAddress(i).toString()));
        }
    }
}
