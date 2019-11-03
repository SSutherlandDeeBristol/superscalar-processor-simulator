package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

public class RegisterFile {

    private List<Register> registers = new ArrayList<>();

    public RegisterFile(Integer numRegisters) {
        for (int i = 0; i < numRegisters; i++) {
            Register r = new Register();
            r.set(0);
            registers.add(r);
        }
    }

    public Register getRegister(Integer i) {
        return this.registers.get(i);
    }

    public boolean validOperands(List<Integer> regNums) {
        for (Integer regNum : regNums) {
            if (regNum == -1)
                continue;

            if (!getRegister(regNum).isValid())
                return false;
        }
        return true;
    }

    public void setValid(List<Integer> regNums, boolean valid) {
        for (Integer regNum : regNums) {
            if (regNum != -1) {
                getRegister(regNum).setValid(valid);
            }
        }
    }

    public String toString() {
        String s = "";
        for (Register r : this.registers) {
            s = s.concat("Register " + registers.indexOf(r) + " : " + r.get() + " | Valid: " + r.isValid() + '\n');
        }
        return s;
    }
}
