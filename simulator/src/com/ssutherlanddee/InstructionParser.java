package com.ssutherlanddee;

public class InstructionParser {

    public enum Operand {Register, Immediate, None};

    public InstructionParser() {

    }

    public Instruction parseInstruction(String i) {

        String trimmed = i.trim();

        if (trimmed.startsWith("add ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.add,
                    Operand.Register, Operand.Register, Operand.Register);
            return new AddInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("sub ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.sub,
                    Operand.Register, Operand.Register, Operand.Register);
            return new SubInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("addi ")) {
            Integer[] operands = parseOperands(trimmed.substring(4), Instruction.Opcode.addi,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new AddImmediateInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("subi ")) {
            Integer[] operands = parseOperands(trimmed.substring(4), Instruction.Opcode.subi,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new SubImmediateInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("mul ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.mul,
                    Operand.Register, Operand.Register, Operand.Register);
            return new MultiplyInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("div ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.div,
                    Operand.Register, Operand.Register, Operand.Register);
            return new DivideInstruction(operands[0], operands[1], operands[2]);
        } else if (trimmed.startsWith("jmp ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.jmp,
                    Operand.Immediate);
            return new JumpInstruction(operands[0]);
        } else if(trimmed.startsWith("ld ")) {
            Integer[] operands = parseOperands(trimmed.substring(2), Instruction.Opcode.ld,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new LoadInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("st ")) {
            Integer[] operands = parseOperands(trimmed.substring(2), Instruction.Opcode.st,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new StoreInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("sti ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.sti,
                    Operand.Register, Operand.Immediate, Operand.Immediate);
            return new StoreImmediateInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("ldi ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.ldi,
                    Operand.Register, Operand.Immediate);
            return new LoadImmediateInstruction(operands[0], operands[1]);
        } else if(trimmed.startsWith("mov ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.mov,
                    Operand.Register, Operand.Register);
            return new MoveInstruction(operands[0], operands[1]);
        } else if(trimmed.startsWith("cmp ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.cmp,
                    Operand.Register, Operand.Register, Operand.Register);
            return new CompareInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("bne ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.bne,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new BranchNotEqualInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("beq ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.beq,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new BranchEqualInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("bgt ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.bgt,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new BranchGreaterThanInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("blt ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.blt,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new BranchLessThanInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("bge ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.bge,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new BranchGreaterThanEqualInstruction(operands[0], operands[1], operands[2]);
        } else if(trimmed.startsWith("ble ")) {
            Integer[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.ble,
                    Operand.Register, Operand.Register, Operand.Immediate);
            return new BranchLessThanEqualInstruction(operands[0], operands[1], operands[2]);
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
            } else if (operandTypes[i] == Operand.Immediate && operands[i].matches("(-)?\\d+")) {
                outputParams[i] = Integer.valueOf(operands[i]);
            } else {
                throw new RuntimeException(opcode + " " + input + ": cannot parse operand " +
                        operands[i] + ", expected " + operandTypes[i] + '\n');
            }
        }

        return outputParams;
    }
}
