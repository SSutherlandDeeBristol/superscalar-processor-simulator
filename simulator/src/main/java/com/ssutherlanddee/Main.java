package com.ssutherlanddee;

public class Main {

    public static void main(String[] args) {

        Program program = new Program(args[0]);
        System.out.println(program.toString());
        Simulator simulator = new Simulator(program);

        simulator.run();
    }
}
