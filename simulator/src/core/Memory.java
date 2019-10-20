package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memory {

    private HashMap<Integer, Object> mainMemory;
    private List<String> instructionMemory;

    public Memory() {
        mainMemory = new HashMap<>();
        instructionMemory = new ArrayList<>();
    }

    public void loadProgramIntoMemory(Program program) {
        instructionMemory.addAll(program.getInstructionList());
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

    public void setMemoryByAddress(Integer address, Object object) {
        this.mainMemory.put(address, object);
    }
}
