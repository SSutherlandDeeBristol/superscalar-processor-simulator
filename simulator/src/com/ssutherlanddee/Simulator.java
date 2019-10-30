package com.ssutherlanddee;

public class Simulator {

    private Processor processor;
    private Program program;

    public Simulator(Program program, boolean interactive) {
        this.program = program;
        this.processor = new Processor(program, interactive);
    }

    public void run() {
        this.processor.process();
    }
}
