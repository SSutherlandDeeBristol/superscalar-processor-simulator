package com.ssutherlanddee;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            Program program = new Program(args[0]);

            boolean interactive = false;
            if (args.length > 1)
                interactive = Boolean.parseBoolean(args[1]);

            Simulator simulator = new Simulator(program, interactive);

            simulator.run();
        } else {
            System.out.println("No assembly file provided. Nothing to do.");
        }
    }
}
