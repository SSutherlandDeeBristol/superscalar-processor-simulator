package core;

import core.instructions.*;

import java.security.DrbgParameters;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    private InstructionParser instructionParser;

    private Memory memory;

    private List<String> encodedInstructions;

    private List<ALUInstruction> aluInstructionBuffer;
    private List<LoadStoreInstruction> loadStoreInstructionBuffer;
    private List<BranchInstruction> branchInstructionBuffer;

    private List<Instruction> toWriteBack;

    private List<ALUnit> ALUExecutionUnits;
    private List<BranchUnit> branchUnits;
    private List<LoadStoreUnit> loadStoreUnits;

    private RegisterFile registerFile;

    private Register PC = new Register();

    public Processor(Program program) {
        this.instructionParser = new InstructionParser();
        this.memory = new Memory();
        this.registerFile = new RegisterFile(10);

        this.memory.loadProgramIntoMemory(program);

        this.encodedInstructions = new ArrayList<>();

        this.aluInstructionBuffer = new ArrayList<>();
        this.loadStoreInstructionBuffer = new ArrayList<>();
        this.branchInstructionBuffer = new ArrayList<>();

        this.toWriteBack = new ArrayList<>();

        this.ALUExecutionUnits = new ArrayList<>();
        this.branchUnits = new ArrayList<>();
        this.loadStoreUnits = new ArrayList<>();

        this.ALUExecutionUnits.add(new ALUnit(0, this.registerFile, this.aluInstructionBuffer));

        this.branchUnits.add(new BranchUnit(0, this.registerFile, this.branchInstructionBuffer));

        this.loadStoreUnits.add(new LoadStoreUnit(0, this.registerFile, this.loadStoreInstructionBuffer));

        System.out.println(program.toString());

        this.memory.setMemoryByAddress(10, 45);

        this.PC.set(0);
    }

    public void process() {

        fetch();

        decode();

        while (unitsHaveBufferedInstructions() || unitsAreExecuting()) {

            execute();

            writeBack();

            printStatus();

            fetch();

            decode();
        }

        System.out.println("register 0: " + registerFile.getRegister(0).get());
        System.out.println("register 4: " + registerFile.getRegister(4).get());
        System.out.println("memory address 10: " + this.memory.getMemoryByAddress(10));
    }

    private void fetch() {
        // fetch instruction from memory and add to buffer

        String nextEncodedInstruction = (String) this.memory.getInstructionByAddress((Integer) this.PC.get());

        if (nextEncodedInstruction == null)
            return;

        this.encodedInstructions.add(nextEncodedInstruction);

        this.PC.set((Integer) this.PC.get() + 1);
    }

    private void decode() {
        while (!this.encodedInstructions.isEmpty()) {
            String s = this.encodedInstructions.remove(0);

            Instruction nextInstruction = instructionParser.parseInstruction(s);

            nextInstruction.setOperands(this.registerFile);

            if (nextInstruction instanceof ALUInstruction) {
                aluInstructionBuffer.add((ALUInstruction) nextInstruction);
            } else if (nextInstruction instanceof BranchInstruction) {
                branchInstructionBuffer.add((BranchInstruction) nextInstruction);
            } else if (nextInstruction instanceof LoadStoreInstruction) {
                loadStoreInstructionBuffer.add((LoadStoreInstruction) nextInstruction);
            } else {
                throw new RuntimeException("Unrecognised instruction.");
            }
        }
    }

    private void execute() {
        this.ALUExecutionUnits.forEach(alUnit -> alUnit.execute(this));

        this.branchUnits.forEach(branchUnit -> branchUnit.execute(this));

        this.loadStoreUnits.forEach(loadStoreUnit -> loadStoreUnit.execute(this));
    }

    public void writeBack() {

    }

    private void printStatus() {
        System.out.println("STATUS\n");
        System.out.println("Arithmetic Logic Units:");
        this.ALUExecutionUnits.forEach(alUnit -> System.out.println("ID " + alUnit.getId() + " | " + alUnit.getStatus()));
        System.out.println("");

        System.out.println("Branch Units:");
        this.branchUnits.forEach(branchUnit -> System.out.println("ID " + branchUnit.getId() + " | " + branchUnit.getStatus()));
        System.out.println("");

        System.out.println("Load/Store Units:");
        this.loadStoreUnits.forEach(loadStoreUnit -> System.out.println("ID " + loadStoreUnit.getId() + " | " + loadStoreUnit.getStatus()));
        System.out.println("");

        System.out.println(this.registerFile.toString());
    }

    private boolean unitsAreExecuting() {
        boolean executing = this.ALUExecutionUnits.stream().anyMatch(ExecutionUnit::isExecuting);
        executing = executing || this.branchUnits.stream().anyMatch(ExecutionUnit::isExecuting);
        executing = executing || this.loadStoreUnits.stream().anyMatch(ExecutionUnit::isExecuting);

        return executing;
    }

    private boolean unitsHaveBufferedInstructions() {
        boolean haveInstructions = this.ALUExecutionUnits.stream().noneMatch(ExecutionUnit::bufferIsEmpty);
        haveInstructions = haveInstructions || this.branchUnits.stream().noneMatch(ExecutionUnit::bufferIsEmpty);
        haveInstructions = haveInstructions || this.loadStoreUnits.stream().noneMatch(ExecutionUnit::bufferIsEmpty);

        return haveInstructions;
    }

    public Memory getMemory() {
        return this.memory;
    }

    public Register getPC() {
        return this.PC;
    }

    public RegisterFile getRegisterFile() {
        return this.registerFile;
    }
}
