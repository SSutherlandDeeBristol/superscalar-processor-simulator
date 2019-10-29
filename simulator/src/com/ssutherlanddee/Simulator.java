package com.ssutherlanddee;

public class Simulator {

    private Processor processor;
    private Program program;

    public Simulator(Program program) {
        this.program = program;
        this.processor = new Processor(program);
    }

    public void run() {

        this.processor.process();
    }
}
