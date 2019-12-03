package com.ssutherlanddee;

import com.ssutherlanddee.Instruction.Opcode;
import com.ssutherlanddee.Operand.OperandType;

public class InstructionParser {

    private Memory memory;

    public InstructionParser(Memory memory) {
        this.memory = memory;
    }

    public Instruction parseInstruction(EncodedInstruction i, Integer tag) {

        String trimmed = i.getInstruction().trim();
        System.out.println(trimmed);

        if (trimmed.startsWith("add ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.add,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new AddInstruction(operands, tag);
        } else if (trimmed.startsWith("sub ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.sub,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new SubInstruction(operands, tag);
        } else if (trimmed.startsWith("addi ")) {
            Operand[] operands = parseOperands(trimmed.substring(4), i.getPC(), Instruction.Opcode.addi,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new AddImmediateInstruction(operands, tag);
        } else if (trimmed.startsWith("subi ")) {
            Operand[] operands = parseOperands(trimmed.substring(4), i.getPC(), Instruction.Opcode.subi,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new SubImmediateInstruction(operands, tag);
        } else if (trimmed.startsWith("mul ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.mul,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new MultiplyInstruction(operands, tag);
        } else if (trimmed.startsWith("div ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.div,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new DivideInstruction(operands, tag);
        } else if (trimmed.startsWith("jmp ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.jmp,
                    OperandType.NONE, OperandType.NONE, OperandType.VALUE);
            return new JumpInstruction(operands, tag);
        } else if(trimmed.startsWith("ld ")) {
            Operand[] operands = parseOperands(trimmed.substring(2), i.getPC(), Instruction.Opcode.ld,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new LoadInstruction(operands, tag);
        } else if(trimmed.startsWith("st ")) {
            Operand[] operands = parseOperands(trimmed.substring(2), i.getPC(), Instruction.Opcode.st,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new StoreInstruction(operands, tag);
        } else if(trimmed.startsWith("sti ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.sti,
                    OperandType.REGISTER, OperandType.VALUE, OperandType.VALUE);
            return new StoreImmediateInstruction(operands, tag);
        } else if(trimmed.startsWith("ldi ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.ldi,
                    OperandType.REGISTER, OperandType.VALUE, OperandType.NONE);
            return new LoadImmediateInstruction(operands, tag);
        } else if(trimmed.startsWith("ldx ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.ldx,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new LoadIndexedInstruction(operands, tag);
        } else if(trimmed.startsWith("stx ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.stx,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new StoreIndexedInstruction(operands, tag);
        } else if(trimmed.startsWith("mov ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.mov,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.NONE);
            return new MoveInstruction(operands, tag);
        } else if(trimmed.startsWith("cmp ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.cmp,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.REGISTER);
            return new CompareInstruction(operands, tag);
        } else if(trimmed.startsWith("bne ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.bne,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchNotEqualInstruction(operands, tag);
        } else if(trimmed.startsWith("beq ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.beq,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchEqualInstruction(operands, tag);
        } else if(trimmed.startsWith("bgt ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.bgt,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchGreaterThanInstruction(operands, tag);
        } else if(trimmed.startsWith("blt ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.blt,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchLessThanInstruction(operands, tag);
        } else if(trimmed.startsWith("bge ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.bge,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchGreaterThanEqualInstruction(operands, tag);
        } else if(trimmed.startsWith("ble ")) {
            Operand[] operands = parseOperands(trimmed.substring(3), i.getPC(), Instruction.Opcode.ble,
                    OperandType.REGISTER, OperandType.REGISTER, OperandType.VALUE);
            return new BranchLessThanEqualInstruction(operands, tag);
        } else {
            throw new RuntimeException("No such instruction as " + trimmed);
        }
    }

    private Operand[] parseOperands(String input, Integer PC, Instruction.Opcode opcode, OperandType... operandTypes) {
        Operand[] outputParams = new Operand[3];

        input = input.trim();
        String[] operands = input.split("\\s+");
        Integer operandsCounter = 0;

        System.out.println(input);

        for (int i = 0; i < operandTypes.length; i++) {
            if (operandTypes[i] == OperandType.NONE) {
                outputParams[i] = new Operand(OperandType.NONE);
            } else if (operandTypes[i] == OperandType.REGISTER && operands[operandsCounter].matches("r\\d\\d?")) {
                outputParams[i] = new Operand(OperandType.REGISTER, Integer.valueOf(operands[operandsCounter].substring(1)));
                operandsCounter++;
            } else if (operandTypes[i] == OperandType.VALUE && this.memory.getPCForLabel(operands[operandsCounter]).isPresent()) {
                if (opcode.equals(Opcode.jmp)) {
                    outputParams[i] = new Operand(OperandType.VALUE, Integer.valueOf(this.memory.getPCForLabel(operands[operandsCounter]).get()));
                } else {
                    outputParams[i] = new Operand(OperandType.VALUE, Integer.valueOf(this.memory.getPCForLabel(operands[operandsCounter]).get() - PC));
                }
                operandsCounter++;
            }else if (operandTypes[i] == OperandType.VALUE && operands[operandsCounter].matches("(-)?\\d+")) {
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
