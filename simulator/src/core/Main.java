package core;

public class Main {

    public static void main(String[] args) {

        Program program = new Program("program.asm");

        Processor processor = new Processor(program);

        processor.process();
    }
}
