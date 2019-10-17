package core;

import core.instructions.AddInstruction;
import core.instructions.Instruction;
import core.instructions.JumpInstruction;
import core.instructions.SubInstruction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

    private List<Instruction> instructionList = new ArrayList<>();
    private enum Operand {Register, Immediate, None};

    public Program(String filename) {
        try {
            URL fileURL = getClass().getResource(filename);
            File programFile = new File(fileURL.getPath());

            BufferedReader reader = new BufferedReader(new FileReader(programFile));

            String s;
            while ((s = reader.readLine()) != null) {
                instructionList.add(parseInstruction(s));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    private Instruction parseInstruction(String i) {

        String trimmed = i.trim();

        if (trimmed.startsWith("ADD ")) {
            Integer[] operands;
            operands = parseOperands(trimmed.substring(3), Instruction.Opcode.ADD, new Operand[] {Operand.Register, Operand.Register, Operand.Register});
            return new AddInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("SUB ")) {
            Integer[] operands;
            operands = parseOperands(i.substring(3), Instruction.Opcode.SUB, new Operand[] {Operand.Register, Operand.Register, Operand.Register});
            return new SubInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("JMP ")) {
            Integer[] operands;
            operands = parseOperands(i.substring(3), Instruction.Opcode.JMP, new Operand[] {Operand.Immediate});
            return new JumpInstruction(operands[0]);
        }

        return null;
    }

    private Integer[] parseOperands(String input, Instruction.Opcode opcode, Operand[] operands) {
        Integer[] outputParams = new Integer[operands.length];

        input = input.trim();
        String[] inputArgs = input.split("\\s+");

        if (inputArgs.length != operands.length)
            throw new RuntimeException(opcode + input + ": instruction of the wrong form. Must have the correct number of operands.\n");

        for (int i = 0; i < operands.length; i++) {
            if (operands[i] == Operand.Register && inputArgs[i].matches("r\\d\\d?")) {
                outputParams[i] = Integer.valueOf(inputArgs[i].substring(1));
            } else if (operands[i] == Operand.Immediate && inputArgs[i].matches("\\d+")) {
                outputParams[i] = Integer.valueOf(inputArgs[i]);
            } else {
                throw new RuntimeException(opcode + " " + input + ": cannot parse operand " + inputArgs[i] + ", expected " + operands[i] + '\n');
            }
        }

        return outputParams;
    }

    public List<Instruction> getInstructionList() {
        return this.instructionList;
    }

    public String toString() {
        String s = "";
        for (Instruction i : this.instructionList) {
            s = s.concat(i.toString() + '\n');
        }
        return s;
    }
}
