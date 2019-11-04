package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Processor {

    private InstructionParser instructionParser;

    private Memory memory;

    private List<String> encodedInstructions;

    private List<Instruction> writeBackBuffer;

    private List<ALUnit> aluUnits;
    private List<BranchUnit> branchUnits;
    private List<LoadStoreUnit> loadStoreUnits;

    private List<ReservationStation> aluRS;
    private List<ReservationStation> branchRS;
    private List<ReservationStation> loadStoreRS;

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

        this.writeBackBuffer = new ArrayList<>();

        this.aluUnits = new ArrayList<>();
        this.branchUnits = new ArrayList<>();
        this.loadStoreUnits = new ArrayList<>();

        this.aluRS = new ArrayList<>();
        this.branchRS = new ArrayList<>();
        this.loadStoreRS = new ArrayList<>();

        this.interactive = interactive;

        constructExecutionUnits(2, 2, 2);

        this.PC.set(0);

        this.running = true;

        System.out.println(program.toString());
    }

    public void process() {
        int step = 0;
        int cycle = 0;

        Scanner input = new Scanner(System.in);

        while (this.running) {

            cycle++;

            if (this.interactive)
                System.out.println("STATUS CYCLE: " + cycle + "\n");

            writeBack();

            execute();

            decode();

            fetch();

            if (!unitsAreExecuting())
                step = (step + 1) % 4;

            this.running = canProcess();

            if (this.interactive) {
                printStatus();
                input.nextLine();
            }
        }

        printFinalStats(cycle);

        input.close();
    }

    private boolean canProcess() {
        return !this.encodedInstructions.isEmpty() || !this.writeBackBuffer.isEmpty()
                || unitsHaveBufferedInstructions() || unitsAreExecuting() || instructionsToFetch() || reservationStationsHaveBufferedInstructions();
    }

    private void fetch() {
        // fetch instruction from memory and add to buffer
        String nextEncodedInstruction = this.memory.getInstructionByAddress(this.PC.get());

        if (nextEncodedInstruction == null) {
            return;
        }

        if (this.interactive)
            System.out.println("FETCHED: " + nextEncodedInstruction);

        this.encodedInstructions.add(nextEncodedInstruction);

        this.PC.increment();
    }

    private void decode() {
        this.aluRS.forEach(ReservationStation::dispatch);
        this.branchRS.forEach(ReservationStation::dispatch);
        this.loadStoreRS.forEach(ReservationStation::dispatch);

        while (!this.encodedInstructions.isEmpty()) {
            String s = this.encodedInstructions.remove(0);

            Instruction nextInstruction = instructionParser.parseInstruction(s);

            if (this.interactive)
                System.out.println("DECODED: " + nextInstruction.toString());

            if (nextInstruction instanceof ALUInstruction) {
                this.aluRS.stream().min((ReservationStation a, ReservationStation b) -> a.getBufferSize() - b.getBufferSize())
                    .get().issue(nextInstruction);

            } else if (nextInstruction instanceof BranchInstruction) {
                this.branchRS.stream().min((ReservationStation a, ReservationStation b) -> a.getBufferSize() - b.getBufferSize())
                .get().issue((BranchInstruction) nextInstruction);

            } else if (nextInstruction instanceof LoadStoreInstruction) {
                this.loadStoreRS.stream().min((ReservationStation a, ReservationStation b) -> a.getBufferSize() - b.getBufferSize())
                .get().issue((LoadStoreInstruction) nextInstruction);

            } else {
                throw new RuntimeException("Unrecognised instruction.");
            }
        }
    }

    private void execute() {
        this.aluUnits.forEach(alUnit -> alUnit.execute(this));

        this.branchUnits.forEach(branchUnit -> branchUnit.execute(this));

        this.loadStoreUnits.forEach(loadStoreUnit -> loadStoreUnit.execute(this));
    }

    public void writeBack() {
        if (this.writeBackBuffer.isEmpty())
            return;

        while (!this.writeBackBuffer.isEmpty()) {
            Instruction i = this.writeBackBuffer.remove(0);

            i.writeBack(this.registerFile);
            i.setDestinationValid(this.registerFile, true);

            if (this.interactive)
                System.out.println("WROTE BACK: " + i.toString());
        }
    }

    private void printStatus() {
        System.out.println("\nArithmetic Logic Units:");
        this.aluUnits.forEach(alUnit -> System.out.println("ID " + alUnit.getId() + " | " + alUnit.getStatus()));

        System.out.println("\nBranch Units:");
        this.branchUnits.forEach(branchUnit -> System.out.println("ID " + branchUnit.getId() + " | " + branchUnit.getStatus()));

        System.out.println("\nLoad/Store Units:");
        this.loadStoreUnits.forEach(loadStoreUnit -> System.out.println("ID " + loadStoreUnit.getId() + " | " + loadStoreUnit.getStatus()));

        System.out.println('\n' + this.registerFile.toString());
    }

    private void printFinalStats(Integer numCycles) {
        Integer numInstructionsExecuted = 0;
        numInstructionsExecuted += this.aluUnits.stream().mapToInt(ExecutionUnit::getNumInstructionsExecuted).sum();
        numInstructionsExecuted += this.branchUnits.stream().mapToInt(ExecutionUnit::getNumInstructionsExecuted).sum();
        numInstructionsExecuted += this.loadStoreUnits.stream().mapToInt(ExecutionUnit::getNumInstructionsExecuted).sum();

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

    private boolean unitsAreExecuting() {
        boolean ALUExecuting = this.aluUnits.stream().anyMatch(ExecutionUnit::isExecuting);
        boolean branchExecuting = this.branchUnits.stream().anyMatch(ExecutionUnit::isExecuting);
        boolean loadStoreExecuting = this.loadStoreUnits.stream().anyMatch(ExecutionUnit::isExecuting);

        return ALUExecuting || branchExecuting || loadStoreExecuting;
    }

    private boolean unitsHaveBufferedInstructions() {
        boolean ALUHaveInstructions = this.aluUnits.stream().anyMatch(ExecutionUnit::bufferNotEmpty);
        boolean branchHaveInstructions = this.branchUnits.stream().anyMatch(ExecutionUnit::bufferNotEmpty);
        boolean loadStoreHaveInstructions = this.loadStoreUnits.stream().anyMatch(ExecutionUnit::bufferNotEmpty);

        return ALUHaveInstructions || branchHaveInstructions || loadStoreHaveInstructions;
    }

    private boolean reservationStationsHaveBufferedInstructions() {
        boolean aluRSHaveInstructions = this.aluRS.stream().anyMatch(ReservationStation::bufferNotEmpty);
        boolean branchRSHaveInstructions = this.branchRS.stream().anyMatch(ReservationStation::bufferNotEmpty);
        boolean loadStoreRSHaveInstructions = this.loadStoreRS.stream().anyMatch(ReservationStation::bufferNotEmpty);

        return aluRSHaveInstructions || branchRSHaveInstructions || loadStoreRSHaveInstructions;
    }

    private void constructExecutionUnits(Integer alu, Integer branch, Integer loadStore) {
        Integer rId = 0;

        for (int a = 0; a < alu; a++) {
            ALUnit e = new ALUnit(a, this.registerFile, this.writeBackBuffer, this.interactive);
            this.aluUnits.add(e);
            this.aluRS.add(new ReservationStation(rId, e, this.registerFile));
            rId++;
        }

        for (int b = 0; b < branch; b++) {
            BranchUnit e = new BranchUnit(b, this.registerFile, this.writeBackBuffer, this.interactive);
            this.branchUnits.add(e);
            this.branchRS.add(new ReservationStation(rId, e, this.registerFile));
            rId++;
        }

        for (int l = 0; l < loadStore; l++) {
            LoadStoreUnit e = new LoadStoreUnit(l, this.registerFile, this.writeBackBuffer, this.interactive);
            this.loadStoreUnits.add(e);
            this.loadStoreRS.add(new ReservationStation(rId, e, this.registerFile));
            rId++;
        }
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
