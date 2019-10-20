package core;

import core.instructions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private List<String> instructionList = new ArrayList<>();

    public Program(String filename) {
        try {
            URL fileURL = getClass().getResource(filename);
            File programFile = new File(fileURL.getPath());

            BufferedReader reader = new BufferedReader(new FileReader(programFile));

            String s;
            while ((s = reader.readLine()) != null) {
                instructionList.add(s);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
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
