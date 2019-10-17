package core;

import core.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Instruction> instructionList = new ArrayList<>();

    public static void main(String[] args) {

        Program program = new Program("program.asm");
        RegisterFile rFile = new RegisterFile(10);
        for (int i = 0; i < 10; i++) {
            rFile.getRegister(i).set(i);
        }

        ExecutionUnit EU = new ExecutionUnit(0, rFile);

        System.out.println(program.toString());
        for (Instruction i : program.getInstructionList()) {
            EU.bufferInstruction(i);
        }

        while (!EU.bufferIsEmpty() || EU.isExecuting()) {
            EU.execute();
            System.out.println(EU.getStatus());
        }

        System.out.println(rFile.getRegister(0).get());
    }
}
