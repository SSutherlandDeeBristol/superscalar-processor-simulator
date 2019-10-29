package com.ssutherlanddee;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private List<String> instructionList = new ArrayList<>();

    public Program(String filepath) {
        Path path = Paths.get(filepath);

        try {
            instructionList = Files.readAllLines(path, StandardCharsets.UTF_8);
            instructionList.stream().map(String::trim);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open or read from: " + filepath);
        }

    }

    public List<String> getInstructionList() {
        return this.instructionList;
    }

    public String toString() {
        String s = "";
        for (String i : this.instructionList) {
            s = s.concat(i + '\n');
        }
        return s;
    }
}
