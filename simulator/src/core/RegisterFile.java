package core;

import java.util.ArrayList;
import java.util.List;

public class RegisterFile {

    private List<Register> registers = new ArrayList<>();
    private Integer PC;

    public RegisterFile(Integer numRegisters) {
        this.PC = 0;
        for (int i = 0; i < numRegisters; i++) {
            registers.add(new Register(i));
        }
    }

    public Register getRegister(Integer i) {
        return this.registers.get(i);
    }

    public Integer getPC() {
        return PC;
    }

    public String toString() {
        String s = "";
        for (Register r : this.registers) {
            s = s.concat("Register " + r.getId() + " : " + r.get() + '\n');
        }
        return s;
    }
}
