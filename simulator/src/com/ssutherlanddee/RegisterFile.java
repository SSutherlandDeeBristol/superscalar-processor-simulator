package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

import com.ssutherlanddee.Operand.OperandType;
import com.ssutherlanddee.Register.State;

public class RegisterFile {

    private List<Register> registers = new ArrayList<>();
    private Register PC = new Register();

    public RegisterFile(Integer numRegisters) {
        for (int i = 0; i < numRegisters; i++) {
            Register r = new Register();
            r.set(0);
            registers.add(r);
        }
        this.PC.set(0);
    }

    public Register getRegister(Integer i) {
        return this.registers.get(i);
    }

    public boolean validOperands(List<Operand> operands) {
        for (Operand o : operands) {
            if (o.getType() == OperandType.REGISTER) {
                if (!getRegister(o.getContents()).isValid())
                    return false;
            }
        }
        return true;
    }

    public void broadcast(Integer register, Integer tag, Integer value) {
        Register r = this.registers.get(register);

        r.setState(State.READY);
    }

    public void flush() {
        this.registers.forEach(r -> r.free());
        this.PC.free();
    }

    public void setPC(Integer value) {
        this.PC.set(value);
    }

    public Register getPC() {
        return this.PC;
    }

    public String toString() {
        String s = String.format("Program Counter: %3d | Valid: %d\n\n", this.PC.get(), this.PC.isValid()? 1 : 0);
        for (Register r : this.registers) {
            s = s.concat(String.format("Register %5d : %3d | Valid: %d\n", registers.indexOf(r), r.get(), r.isValid() ? 1 : 0));
        }
        return s;
    }
}
