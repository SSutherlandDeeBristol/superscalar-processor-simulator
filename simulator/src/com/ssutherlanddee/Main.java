package com.ssutherlanddee;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            Program program = new Program(args[0]);

            boolean interactive = false;
            if (args.length > 1)
                interactive = Boolean.parseBoolean(args[1]);

            Integer width = 4;
            if (args.length > 2)
                width = Integer.valueOf(args[2]);

            System.out.println(width);

            Processor processor = new Processor(program, interactive, width);

            processor.process();
        } else {
            System.out.println("No assembly file provided. Nothing to do.");
        }
    }
}
