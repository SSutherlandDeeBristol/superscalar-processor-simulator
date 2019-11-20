package com.ssutherlanddee;

import com.ssutherlanddee.Operand.OperandType;

public class InstructionParser {

    public InstructionParser() {}

    public Instruction parseInstruction(String i, Integer tag) {

        String trimmed = i.trim();

        if (trimmed.startsWith("add ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.add,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new AddInstruction(operands, tag);
        } else if (trimmed.startsWith("sub ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.sub,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new SubInstruction(operands, tag);
        } else if (trimmed.startsWith("addi ")) {
            Operand[] operands = parseOperands(trimmed.substring(4), Instruction.Opcode.addi,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new AddImmediateInstruction(operands, tag);
        } else if (trimmed.startsWith("subi ")) {
            Operand[] operands = parseOperands(trimmed.substring(4), Instruction.Opcode.subi,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new SubImmediateInstruction(operands, tag);
        } else if (trimmed.startsWith("mul ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.mul,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new MultiplyInstruction(operands, tag);
        } else if (trimmed.startsWith("div ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.div,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new DivideInstruction(operands, tag);
        } else if (trimmed.startsWith("jmp ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.jmp,
                    OperandType.NONE, OperandType.NONE, OperandType.VALUE);
            return new JumpInstruction(operands, tag);
        } else if(trimmed.startsWith("ld ")) {
            Operand[] operands = parseOperands(trimmed.substring(2), Instruction.Opcode.ld,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new LoadInstruction(operands, tag);
        } else if(trimmed.startsWith("st ")) {
            Operand[] operands = parseOperands(trimmed.substring(2), Instruction.Opcode.st,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new StoreInstruction(operands, tag);
        } else if(trimmed.startsWith("sti ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.sti,
                    OperandType.REGISTER, OperandType.VALUE, OperandType.VALUE);
            return new StoreImmediateInstruction(operands, tag);
        } else if(trimmed.startsWith("ldi ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.ldi,
            OperandType.REGISTER, OperandType.VALUE, OperandType.NONE);
            return new LoadImmediateInstruction(operands, tag);
        } else if(trimmed.startsWith("mov ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.mov,
                OperandType.REGISTER, OperandType.REGISTER, OperandType.NONE);
            return new MoveInstruction(operands, tag);
        } else if(trimmed.startsWith("cmp ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.cmp,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new CompareInstruction(operands, tag);
        } else if(trimmed.startsWith("bne ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.bne,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchNotEqualInstruction(operands, tag);
        } else if(trimmed.startsWith("beq ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.beq,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchEqualInstruction(operands, tag);
        } else if(trimmed.startsWith("bgt ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.bgt,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchGreaterThanInstruction(operands, tag);
        } else if(trimmed.startsWith("blt ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.blt,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchLessThanInstruction(operands, tag);
        } else if(trimmed.startsWith("bge ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.bge,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchGreaterThanEqualInstruction(operands, tag);
        } else if(trimmed.startsWith("ble ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), Instruction.Opcode.ble,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchLessThanEqualInstruction(operands, tag);
        } else {
            throw new RuntimeException("No such instruction as " + trimmed);
        }
    }

    private Operand[] parseOperands(String input, Instruction.Opcode opcode, OperandType... operandTypes) {
        Operand[] outputParams = new Operand[3];

        input = input.trim();
        String[] operands = input.split("\\s+");
        Integer operandsCounter = 0;

        for (int i = 0; i < operandTypes.length; i++) {
            if (operandTypes[i] == OperandType.NONE) {
                outputParams[i] = new Operand(OperandType.NONE);
            } else if (operandTypes[i] == OperandType.REGISTER && operands[operandsCounter].matches("r\\d\\d?")) {
                outputParams[i] = new Operand(OperandType.REGISTER, Integer.valueOf(operands[operandsCounter].substring(1)));
                operandsCounter++;
            } else if (operandTypes[i] == OperandType.VALUE && operands[operandsCounter].matches("(-)?\\d+")) {
                outputParams[i] = new Operand(OperandType.VALUE, Integer.valueOf(operands[operandsCounter]));
                operandsCounter++;
            } else {
                throw new RuntimeException(opcode + " " + input + ": cannot parse operand " +
                        operands[i] + ", expected " + operandTypes[i] + '\n');
            }
        }

        return outputParams;
    }
}
