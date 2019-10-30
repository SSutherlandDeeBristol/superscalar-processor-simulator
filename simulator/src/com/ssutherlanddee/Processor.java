package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private boolean running;

    private boolean interactive;

    public Processor(Program program, boolean interactive) {
        this.instructionParser = new InstructionParser();
        this.memory = new Memory();
        this.registerFile = new RegisterFile(10);

        this.memory.loadProgramIntoMemory(program);

        this.encodedInstructions = new ArrayList<>();

        this.toWriteBack = new ArrayList<>();

        this.ALUExecutionUnits = new ArrayList<>();
        this.branchUnits = new ArrayList<>();
        this.loadStoreUnits = new ArrayList<>();

        this.ALUExecutionUnits.add(new ALUnit(0, this.registerFile, this.toWriteBack));

        this.branchUnits.add(new BranchUnit(0, this.registerFile, this.toWriteBack));

        this.loadStoreUnits.add(new LoadStoreUnit(0, this.registerFile, this.toWriteBack));

        System.out.println(program.toString());

        this.memory.setMemoryByAddress(10, 45);

        this.PC.set(0);

        this.running = true;

        this.interactive = interactive;
    }

    public void process() {
        int step = 0;
        int numCycles = 0;
        int numInstructionsExecuted = 0;

        Scanner input = new Scanner(System.in);

        while (this.running) {
            switch (step) {
                case 0:
                    fetch();
                    break;
                case 1:
                    decode();
                    break;
                case 2:
                    execute();
                    numInstructionsExecuted++;
                    break;
                case 3:
                    writeBack();
                    break;
            }

            numCycles++;

            if (!unitsAreExecuting())
                step = (step + 1) % 4;

            this.running = canProcess();

            if (this.interactive) {
                printStatus(numCycles);
                input.nextLine();
            }
        }

        printFinalStats(numCycles, numInstructionsExecuted);

        input.close();
    }

    private boolean canProcess() {
        return !this.encodedInstructions.isEmpty() || !this.toWriteBack.isEmpty()
                || unitsHaveBufferedInstructions() || unitsAreExecuting() || instructionsToFetch();
    }

    private void fetch() {
        // fetch instruction from memory and add to buffer
        String nextEncodedInstruction = this.memory.getInstructionByAddress(this.PC.get());

        if (nextEncodedInstruction == null) {
            return;
        }

        if (this.interactive)
            System.out.println("Fetched " + nextEncodedInstruction);

        this.encodedInstructions.add(nextEncodedInstruction);

        this.PC.increment();
    }

    private void decode() {
        while (!this.encodedInstructions.isEmpty()) {
            String s = this.encodedInstructions.remove(0);

            Instruction nextInstruction = instructionParser.parseInstruction(s);

            if (this.interactive)
                System.out.println("Decoded into: " + nextInstruction.toString());

            if (nextInstruction instanceof ALUInstruction) {
                this.ALUExecutionUnits.stream().min((ExecutionUnit a, ExecutionUnit b) -> a.getBufferSize() - b.getBufferSize())
                    .get().bufferInstruction((ALUInstruction) nextInstruction);

                if (this.interactive)
                    System.out.println("Added " + nextInstruction.toString() + " to ALU Instruction buffer");
            } else if (nextInstruction instanceof BranchInstruction) {
                this.branchUnits.stream().min((ExecutionUnit a, ExecutionUnit b) -> a.getBufferSize() - b.getBufferSize())
                .get().bufferInstruction((BranchInstruction) nextInstruction);

                if (this.interactive)
                    System.out.println("Added " + nextInstruction.toString() + " to Branch Instruction buffer");
            } else if (nextInstruction instanceof LoadStoreInstruction) {
                this.loadStoreUnits.stream().min((ExecutionUnit a, ExecutionUnit b) -> a.getBufferSize() - b.getBufferSize())
                .get().bufferInstruction((LoadStoreInstruction) nextInstruction);

                if (this.interactive)
                    System.out.println("Added " + nextInstruction.toString() + " to Load/Store Instruction buffer");
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
        if (this.toWriteBack.isEmpty())
            return;

        Instruction i = this.toWriteBack.remove(0);

        i.writeBack(this.registerFile);

        if (this.interactive)
            System.out.println("Wrote back: " + i.toString());
    }

    private void printStatus(Integer cycle) {
        System.out.println("STATUS CYCLE: " + cycle + "\n");
        System.out.println("Arithmetic Logic Units:");
        this.ALUExecutionUnits.forEach(alUnit -> System.out.println("ID " + alUnit.getId() + " | " + alUnit.getStatus()));
        System.out.println();

        System.out.println("Branch Units:");
        this.branchUnits.forEach(branchUnit -> System.out.println("ID " + branchUnit.getId() + " | " + branchUnit.getStatus()));
        System.out.println();

        System.out.println("Load/Store Units:");
        this.loadStoreUnits.forEach(loadStoreUnit -> System.out.println("ID " + loadStoreUnit.getId() + " | " + loadStoreUnit.getStatus()));
        System.out.println();

        System.out.println(this.registerFile.toString());
    }

    private void printFinalStats(Integer numCycles, Integer numInstructionsExecuted) {
        System.out.println("Number of cycles: " + numCycles);
        System.out.println("Number of instructions executed: " + numInstructionsExecuted);
        if (numInstructionsExecuted > 0)
            System.out.println("Number of cycles per instruction: " + (numCycles / numInstructionsExecuted));

        System.out.println("Number of instructions per cycle: " + ((float) numInstructionsExecuted / numCycles) + "\n");

        System.out.println("Final register state:");
        System.out.println(this.registerFile.toString());
    }

    private boolean instructionsToFetch() {
        return this.memory.instructionsRemaining(this.PC.get()) > 0;
    }

    public boolean blocked() {
        return !(unitsAreExecuting() || unitsHaveBufferedInstructions());
    }

    private boolean unitsAreExecuting() {
        boolean ALUExecuting = this.ALUExecutionUnits.stream().anyMatch(ExecutionUnit::isExecuting);
        boolean branchExecuting = this.branchUnits.stream().anyMatch(ExecutionUnit::isExecuting);
        boolean loadStoreExecuting = this.loadStoreUnits.stream().anyMatch(ExecutionUnit::isExecuting);

        return ALUExecuting || branchExecuting || loadStoreExecuting;
    }

    private boolean unitsHaveBufferedInstructions() {
        boolean ALUHaveInstructions = this.ALUExecutionUnits.stream().noneMatch(ExecutionUnit::bufferIsEmpty);
        boolean branchHaveInstructions = this.branchUnits.stream().noneMatch(ExecutionUnit::bufferIsEmpty);
        boolean loadStoreHaveInstructions = this.loadStoreUnits.stream().noneMatch(ExecutionUnit::bufferIsEmpty);

        return ALUHaveInstructions || branchHaveInstructions || loadStoreHaveInstructions;
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
