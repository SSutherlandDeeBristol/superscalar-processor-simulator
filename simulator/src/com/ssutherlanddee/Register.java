package com.ssutherlanddee;

import com.ssutherlanddee.Operand.OperandType;

public class Register {

    private Integer contents;
    private boolean valid;
    private Integer tag;

    public Register() {
        this.contents = 0;
        this.valid = true;
        this.tag = -1;
    }

    public void block(Integer tag) {
        this.valid = false;
        this.tag = tag;
    }

    public void free() {
        this.valid = true;
        this.tag = -1;
    }

    public Operand poll() {
        if (this.isValid()) {
            return new Operand(OperandType.VALUE, this.contents);
        } else {
            return new Operand(OperandType.TAG, this.tag);
        }
    }

    public Integer get() {
        return contents;
    }

    public void set(Integer contents) {
        this.contents = contents;
    }

    public void increment() {
        this.contents++;
    }

    public void decrement() {
        this.contents--;
    }

    public boolean isValid() {
        return this.valid;
    }

    public Integer getTag() {
        return this.tag;
    }
}
