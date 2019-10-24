package core;

public class Main {

    public static void main(String[] args) {

        Program program = new Program("program2.asm");
        System.out.println(program.toString());
        Simulator simulator = new Simulator(program);

        simulator.run();
    }
}
