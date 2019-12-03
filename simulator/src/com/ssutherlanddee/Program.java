package com.ssutherlanddee;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    private List<String> instructionList;
    private HashMap<String, Integer> labelMap;

    public Program(String filepath) {
        Path path = Paths.get(filepath);

        this.instructionList = new ArrayList<>();
        this.labelMap = new HashMap<>();

        try {
            this.instructionList = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open or read from: " + filepath);
        }

        this.instructionList = instructionList.stream().map(this::removeComment)
                                        .filter(this::hasContent).collect(Collectors.toList());

        Integer numLabels = 0;
        for (int i = 0; i < this.instructionList.size(); i++) {
            if (isLabel(this.instructionList.get(i))) {
                this.labelMap.put(this.instructionList.get(i).substring(0, this.instructionList.get(i).length() - 1), i - numLabels);
                numLabels++;
            }
        }

        this.instructionList = this.instructionList.stream().filter(this::isNotLabel).collect(Collectors.toList());
    }

    public boolean hasContent(String line) {
        return !line.trim().isEmpty();
    }

    public String removeComment(String line) {
        return line.split("#")[0].trim();
    }

    public boolean isLabel(String line) {
        return line.endsWith(":");
    }

    public boolean isNotLabel(String line) {
        return !isLabel(line);
    }

    public List<String> getInstructionList() {
        return this.instructionList;
    }

    public HashMap<String, Integer> getLabelMap() {
        return this.labelMap;
    }

    public String toString() {
        String s = "";
        for (String i : this.instructionList) {
            s = s.concat(i + '\n');
        }
        return s;
    }
}
