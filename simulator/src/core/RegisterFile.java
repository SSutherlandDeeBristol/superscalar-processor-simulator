package core;

import java.util.ArrayList;
import java.util.List;

public class RegisterFile {

    private List<Register> registers = new ArrayList<>();

    public RegisterFile(Integer numRegisters) {
        for (int i = 0; i < numRegisters; i++) {
            Register r = new Register();
            r.set(i);
            registers.add(r);
        }
    }

    public Register getRegister(Integer i) {
        return this.registers.get(i);
    }

    public String toString() {
        String s = "";
        for (Register r : this.registers) {
            s = s.concat("Register " + registers.indexOf(r) + " : " + r.get() + '\n');
        }
        return s;
    }
}
