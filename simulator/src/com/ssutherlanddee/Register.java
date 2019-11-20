package com.ssutherlanddee;

import com.ssutherlanddee.Operand.OperandType;

public class Register {
    public enum State {AVAILABLE, INFLIGHT, READY}

    private Integer contents;
    private boolean valid;
    private Integer tag;

    private State state;

    public Register() {
        this.contents = 0;
        this.valid = true;
        this.tag = -1;
        this.state = State.AVAILABLE;
    }

    public void block(Integer tag) {
        this.valid = false;
        this.tag = tag;
        this.state = State.INFLIGHT;
    }

    public void free() {
        this.valid = true;
        this.tag = -1;
        this.state = State.AVAILABLE;
    }

    public Operand poll() {
        if (this.state == State.AVAILABLE) {
            return new Operand(OperandType.VALUE, this.contents);
        } else if (this.state == State.INFLIGHT) {
            return new Operand(OperandType.TAG, this.tag);
        } else {
            return new Operand(OperandType.ROB, this.tag);
        }
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
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
