package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

import com.ssutherlanddee.Register.State;

public class RegisterFile {

    private List<Register> registers = new ArrayList<>();
    private Register PC = new Register(-1);

    public RegisterFile(Integer numRegisters) {
        for (int i = 0; i < numRegisters; i++) {
            Register r = new Register(i);
            r.set(0);
            registers.add(r);
        }
        this.PC.set(0);
    }

    public Register getRegister(Integer i) {
        return this.registers.get(i);
    }

    public void broadcast(Integer register, Integer tag, Integer value) {
        Register r = this.registers.get(register);

        r.setState(State.READY);
    }

    public void flush() {
        this.registers.forEach(r -> r.setState(State.AVAILABLE));
        this.PC.setState(State.AVAILABLE);
    }

    public void setPC(Integer value) {
        this.PC.set(value);
    }

    public Register getPC() {
        return this.PC;
    }

    public String toString() {
        String s = String.format("Program Counter: %3d | Status: %s\n\n", this.PC.get(), this.PC.getState().toString());
        for (Register r : this.registers) {
            s = s.concat(String.format("Register %5d : %3d | Status: %s\n", registers.indexOf(r), r.get(), r.getState().toString()));
        }
        return s;
    }
}
