package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.ssutherlanddee.BranchPredictorFactory.PredictorType;

public class Processor {

    private InstructionParser instructionParser;

    private Memory memory;

    private List<EncodedInstruction> encodedInstructions;

    private List<ALUnit> aluUnits;
    private List<BranchUnit> branchUnits;
    private List<LoadStoreUnit> loadStoreUnits;

    private List<ReservationStation> aluRS;
    private List<ReservationStation> branchRS;
    private List<ReservationStation> loadStoreRS;

    private ReorderBuffer reorderBuffer;

    private RegisterFile registerFile;

    private TagManager tagManager;

    private BranchPredictor branchPredictor;

    private boolean running;

    private boolean interactive;

    private Integer fetchWidth;

    private Integer numFlushes;

    public Processor(Program program, boolean interactive) {
        this.instructionParser = new InstructionParser();
        this.memory = new Memory();
        this.registerFile = new RegisterFile(16);

        this.memory.loadProgramIntoMemory(program);

        this.encodedInstructions = new ArrayList<>();

        this.aluUnits = new ArrayList<>();
        this.branchUnits = new ArrayList<>();
        this.loadStoreUnits = new ArrayList<>();

        this.aluRS = new ArrayList<>();
        this.branchRS = new ArrayList<>();
        this.loadStoreRS = new ArrayList<>();

        this.reorderBuffer = new ReorderBuffer(this.registerFile, this.memory, 48);

        this.tagManager = new TagManager();

        BranchPredictorFactory branchPredictorFactory = new BranchPredictorFactory();

        this.branchPredictor = branchPredictorFactory.create(PredictorType.FIXEDNT);

        this.interactive = interactive;

        constructExecutionUnits(2, 1, 2);

        this.running = true;

        this.fetchWidth = 4;

        this.numFlushes = 0;

        System.out.println(program.toString());
    }

    public void process() {
        int cycle = 0;

        Scanner input = new Scanner(System.in);

        while (this.running) {

            cycle++;

            if (this.interactive)
                System.out.println("STATUS | CYCLE: " + cycle + "\n");

            writeBack();

            execute();

            decode();

            fetch();

            if (this.interactive) {
                printStatus();
                input.nextLine();
            }

            this.running = canProcess();
        }

        printFinalStats(cycle);

        input.close();
    }

    private boolean canProcess() {
        return !this.encodedInstructions.isEmpty() || !this.reorderBuffer.isEmpty()
                || unitsHaveBufferedInstructions() || unitsAreExecuting() || instructionsToFetch()
                || reservationStationsHaveBufferedInstructions();
    }

    private void fetch() {
        for (int i = 0; i < this.fetchWidth; i++) {
            String nextEncodedInstruction = this.memory.getInstructionByAddress(this.registerFile.getPC().get());

            if (nextEncodedInstruction == null)
                return;

            if (this.reorderBuffer.getSize() + this.encodedInstructions.size() > this.reorderBuffer.getCapacity())
                return;

            if (this.interactive)
                System.out.println("FETCHED: " + nextEncodedInstruction);

            Optional<Pair<Integer, Boolean>> branchInfo = this.branchPredictor.predict(nextEncodedInstruction, this.registerFile.getPC().get());

            // If the instruction is a conditional branch and we should branch
            if (branchInfo.isPresent()) {
                this.encodedInstructions.add(new EncodedInstruction(this.registerFile.getPC().get(),
                    nextEncodedInstruction, true));

                this.registerFile.getPC().set(branchInfo.get().first());
            } else {
                this.encodedInstructions.add(new EncodedInstruction(this.registerFile.getPC().get(),
                    nextEncodedInstruction, false));

                this.registerFile.getPC().increment();
            }
        }
    }

    private void decode() {
        this.aluRS.forEach(ReservationStation::dispatch);
        this.branchRS.forEach(ReservationStation::dispatch);
        this.loadStoreRS.forEach(ReservationStation::dispatch);

        for (int i = 0; i < 4; i++) {
            if (this.encodedInstructions.isEmpty())
                return;

            EncodedInstruction encodedInstruction = this.encodedInstructions.remove(0);

            Integer tag = this.tagManager.getFreeTag();

            Instruction nextInstruction = instructionParser.parseInstruction(encodedInstruction.getInstruction(), tag);

            Comparator<ReservationStation> compareRS = new Comparator<ReservationStation>() {
                public int compare(ReservationStation a, ReservationStation b) {
                    boolean aIsExecuting = a.getExecutionUnit().isExecuting();
                    boolean bIsExecuting = b.getExecutionUnit().isExecuting();

                    int aVal = (a.getBufferSize() + a.getExecutionUnit().getBufferSize());
                    int bVal = (b.getBufferSize() + b.getExecutionUnit().getBufferSize());

                    return aVal - bVal;
                }
            };

            if (this.interactive)
                System.out.println("DECODED: " + nextInstruction.toString());

            boolean noCapacity = false;

            if (nextInstruction instanceof ALUInstruction) {
                ReservationStation rs = this.aluRS.stream().min(compareRS).get();

                if (!rs.hasCapacity()) {
                    noCapacity = true;
                } else {
                    rs.issue(nextInstruction, encodedInstruction.getPC());
                }
            } else if (nextInstruction instanceof BranchInstruction) {
                ReservationStation rs = this.branchRS.stream().min(compareRS).get();

                if (!rs.hasCapacity()) {
                    noCapacity = true;
                } else {
                    ((BranchInstruction) nextInstruction).setPrediction(encodedInstruction.getPredictedBranch());
                    rs.issue(nextInstruction, encodedInstruction.getPC());
                }
            } else if (nextInstruction instanceof LoadStoreInstruction) {
                ReservationStation rs = this.loadStoreRS.stream().min(compareRS).get();

                if (!rs.hasCapacity()) {
                    noCapacity = true;
                } else {
                    rs.issue(nextInstruction, encodedInstruction.getPC());
                }
            } else {
                throw new RuntimeException("Unrecognised instruction type.");
            }

            if (noCapacity) {
                this.encodedInstructions.add(0, encodedInstruction);
                break;
            }

        }
    }

    private void execute() {
        this.aluUnits.forEach(alUnit -> alUnit.execute(this));

        this.branchUnits.forEach(branchUnit -> branchUnit.execute(this));

        this.loadStoreUnits.forEach(loadStoreUnit -> loadStoreUnit.execute(this));
    }

    public void writeBack() {
        this.reorderBuffer.retire(this.interactive, this);
    }

    public void broadcast(Integer register, Integer tag, Integer value) {
        this.aluRS.forEach(rs -> rs.broadcast(tag, value));
        this.branchRS.forEach(rs -> rs.broadcast(tag, value));
        this.loadStoreRS.forEach(rs -> rs.broadcast(tag, value));
        this.registerFile.broadcast(register, tag, value);
    }

    public void flushPipeline() {
        this.encodedInstructions.clear();

        this.registerFile.flush();

        this.aluRS.forEach(ReservationStation::flush);
        this.branchRS.forEach(ReservationStation::flush);
        this.loadStoreRS.forEach(ReservationStation::flush);

        this.aluUnits.forEach(ExecutionUnit::flush);
        this.branchUnits.forEach(ExecutionUnit::flush);
        this.loadStoreUnits.forEach(ExecutionUnit::flush);

        this.reorderBuffer.flush();

        this.tagManager.flush();

        this.numFlushes++;
    }

    private void printStatus() {
        System.out.println("\nArithmetic Logic Units:");
        this.aluUnits.forEach(alUnit -> System.out.println("ID " + alUnit.getId() + " | " + alUnit.getStatus()));

        System.out.println("\nBranch Units:");
        this.branchUnits.forEach(branchUnit -> System.out.println("ID " + branchUnit.getId() + " | " + branchUnit.getStatus()));

        System.out.println("\nLoad/Store Units:");
        this.loadStoreUnits.forEach(loadStoreUnit -> System.out.println("ID " + loadStoreUnit.getId() + " | " + loadStoreUnit.getStatus()));

        System.out.println('\n' + this.registerFile.toString());

        System.out.println("Reservation Stations: ");
        System.out.println("ALU: ");
        this.aluRS.forEach(ReservationStation::printContents);
        System.out.println("\nBranch: ");
        this.branchRS.forEach(ReservationStation::printContents);
        System.out.println("\nLoad/Store: ");
        this.loadStoreRS.forEach(ReservationStation::printContents);

        System.out.println("\nReorder Buffer: ");
        this.reorderBuffer.printContents();

        System.out.println("\nBranch Target Address Cache: ");
        this.branchPredictor.printCache();

        System.out.println("\nMemory: ");
        this.memory.printContents(10);
    }

    private void printFinalStats(Integer numCycles) {
        Integer numInstructionsExecuted = 0;
        numInstructionsExecuted += this.aluUnits.stream().mapToInt(ExecutionUnit::getNumInstructionsExecuted).sum();
        numInstructionsExecuted += this.branchUnits.stream().mapToInt(ExecutionUnit::getNumInstructionsExecuted).sum();
        numInstructionsExecuted += this.loadStoreUnits.stream().mapToInt(ExecutionUnit::getNumInstructionsExecuted).sum();

        Integer numInstructionsCompleted = this.reorderBuffer.numInstructionsCompleted();

        System.out.println("Number of cycles: " + numCycles);
        System.out.println("Number of instructions executed: " + numInstructionsExecuted);
        System.out.println("Number of instructions completed: " + numInstructionsCompleted + "\n");

        if (numInstructionsCompleted > 0)
            System.out.println(String.format("Number of cycles per completed instruction: %.2f", ((float) numCycles / numInstructionsCompleted)));

        System.out.println(String.format("Number of instructions completed per cycle: %.2f", ((float) numInstructionsCompleted / numCycles)));
        System.out.println(String.format("Number of instructions executed per cycle: %.2f\n", ((float) numInstructionsExecuted / numCycles)));

        System.out.println(String.format("Number of mispredicted branches: %d", this.numFlushes));
        System.out.println(String.format("Number of branches completed: %d", this.reorderBuffer.getNumBranchInstructionsCompleted()));
        if (this.reorderBuffer.getNumBranchInstructionsCompleted() > 0)
            System.out.println(String.format("Misprediction rate: %.2f%%", ((float) 100.0f * ((float) this.numFlushes / this.reorderBuffer.getNumBranchInstructionsCompleted()))));

        System.out.println("\nFinal register state:\n");
        System.out.println(this.registerFile.toString());
    }

    private boolean instructionsToFetch() {
        return this.memory.instructionsRemaining(this.getRegisterFile().getPC().get()) > 0;
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
            ALUnit e = new ALUnit(a, this.registerFile, this.interactive);
            this.aluUnits.add(e);
            this.aluRS.add(new ReservationStation(rId, e, this.registerFile, this.reorderBuffer, 32));
            rId++;
        }

        for (int b = 0; b < branch; b++) {
            BranchUnit e = new BranchUnit(b, this.registerFile, this.interactive);
            this.branchUnits.add(e);
            this.branchRS.add(new ReservationStation(rId, e, this.registerFile, this.reorderBuffer, 32));
            rId++;
        }

        for (int l = 0; l < loadStore; l++) {
            LoadStoreUnit e = new LoadStoreUnit(l, this.registerFile, this.interactive);
            this.loadStoreUnits.add(e);
            this.loadStoreRS.add(new ReservationStation(rId, e, this.registerFile, this.reorderBuffer, 32));
            rId++;
        }
    }

    public Memory getMemory() {
        return this.memory;
    }

    public Register getPC() {
        return this.registerFile.getPC();
    }

    public RegisterFile getRegisterFile() {
        return this.registerFile;
    }

    public TagManager getTagManager() {
        return this.tagManager;
    }

    public BranchPredictor getBranchPredictor() {
        return this.branchPredictor;
    }
}
