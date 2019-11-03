package com.ssutherlanddee;

public class Register {

    private Integer contents;
    private boolean valid;

    public Register() {
        this.contents = 0;
        this.valid = true;
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

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
