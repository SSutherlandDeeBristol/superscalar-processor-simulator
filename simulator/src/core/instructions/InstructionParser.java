package core.instructions;

import core.Program;

public class InstructionParser {

    public enum Operand {Register, Immediate, None};

    public InstructionParser() {

    }

    public Instruction parseInstruction(String i) {

        String trimmed = i.trim();

        if (trimmed.startsWith("ADD ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.ADD,
                    Operand.Register, Operand.Register, Operand.Register);
            return new AddInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("SUB ")) {
            Integer[] operands = parseOperands(i.substring(3), Instruction.Opcode.SUB,
                    Operand.Register, Operand.Register, Operand.Register);
            return new SubInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("JMP ")) {
            Integer[] operands = parseOperands(i.substring(3), Instruction.Opcode.JMP,
                    Operand.Immediate);
            return new JumpInstruction(operands[0]);
        } else if(trimmed.startsWith("LD ")) {
            Integer[] operands = parseOperands(i.substring(2), Instruction.Opcode.LD,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new LoadInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("ST ")) {
            Integer[] operands = parseOperands(i.substring(2), Instruction.Opcode.ST,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new StoreInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("LDI ")) {
            Integer[] operands = parseOperands(i.substring(3), Instruction.Opcode.LDI,
                    Operand.Register, Operand.Immediate);
            return new LoadImmediateInstruction(operands[0], operands[1]);
        } else if(trimmed.startsWith("MOV ")) {
            Integer[] operands = parseOperands(i.substring(3), Instruction.Opcode.MOV,
                    Operand.Register, Operand.Register);
            return new MoveInstruction(operands[0], operands[1]);
        } else if(trimmed.startsWith("CMP ")) {
            Integer[] operands = parseOperands(i.substring(3), Instruction.Opcode.CMP,
                    Operand.Register, Operand.Register, Operand.Register);
            return new CompareInstruction(operands[0], operands[1], operands[2]);
        } else {
            throw new RuntimeException("No such instruction as " + trimmed);
        }
    }

    private Integer[] parseOperands(String input, Instruction.Opcode opcode, Operand... operandTypes) {
        Integer[] outputParams = new Integer[operandTypes.length];

        input = input.trim();
        String[] operands = input.split("\\s+");

        if (operands.length != operandTypes.length)
            throw new RuntimeException(opcode + input + ": instruction of the wrong form. " +
                    "Must have the correct number of operands.\n");

        for (int i = 0; i < operandTypes.length; i++) {
            if (operandTypes[i] == Operand.Register && operands[i].matches("r\\d\\d?")) {
                outputParams[i] = Integer.valueOf(operands[i].substring(1));
            } else if (operandTypes[i] == Operand.Immediate && operands[i].matches("\\d+")) {
                outputParams[i] = Integer.valueOf(operands[i]);
            } else {
                throw new RuntimeException(opcode + " " + input + ": cannot parse operand " +
                        operands[i] + ", expected " + operandTypes[i] + '\n');
            }
        }

        return outputParams;
    }
}
