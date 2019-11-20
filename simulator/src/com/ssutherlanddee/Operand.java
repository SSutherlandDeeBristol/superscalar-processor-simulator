package com.ssutherlanddee;

public class Operand {
    public enum OperandType {TAG, VALUE, REGISTER, NONE}

    protected OperandType type;
    protected Integer contents;

    public Operand(OperandType type) {
        this.type = type;
        this.contents = 0;
    }

    public Operand(OperandType type, Integer contents) {
        this.type = type;
        this.contents = contents;
    }

    public OperandType getType() {
        return this.type;
    }

    public void setType(OperandType type, Integer contents) {
        this.type = type;
        this.contents = contents;
    }

    public void setType(OperandType type) {
        this.type = type;
        this.contents = 0;
    }

    public Integer getContents() {
        return this.contents;
    }

    public void setContents(Integer contents) {
        this.contents = contents;
    }

    public boolean isReady() {
        return this.type == OperandType.NONE || this.type == OperandType.VALUE;
    }

    public String toString() {
        if (this.type == OperandType.REGISTER) {
            return "r" + this.contents;
        } else if (this.type == OperandType.VALUE) {
            return String.valueOf(this.contents);
        } else if (this.type == OperandType.TAG) {
            return "t" + this.contents;
        } else {
            return "";
        }
    }
}