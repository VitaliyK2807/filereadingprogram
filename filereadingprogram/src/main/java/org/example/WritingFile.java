package org.example;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WritingFile {

    private String stringPath;
    private String prefix;
    private Boolean addingFiles;

    public WritingFile(String stringPath, String prefix, Boolean addingFiles) {
        this.stringPath = stringPath;
        this.prefix = prefix;
        this.addingFiles = addingFiles;
    }

    public void writingFile(List<String> list, String fileName) {
        if (!list.isEmpty()) {
            delimiter();
            String readyPath = stringPath + prefix + fileName;
            System.out.println("Writing to a file");
            writingInFile(getStringToWrite(list), readyPath);
        }
    }

    private String getStringToWrite(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        strings.forEach(string -> builder.append(string + "\n"));
        return builder.toString();
    }

    private void writingInFile(String string, String pathString) {
        Path path = Paths.get(pathString);
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        try {
            if (!Files.exists(path) || addingFiles) {
                Files.write(path, bytes, StandardOpenOption.APPEND);
                System.out.println("File created: " + path);
            } else {
                Files.delete(path);
                Files.write(path, bytes);
                System.out.println("File overwritten: " + path);
            }
        } catch (Exception ex) {
            System.out.println("Error writing file: " + path);
            ex.getMessage();

        }
    }

    private static void delimiter() {
        System.out.println("----------|--------------|-------------|-----------");
    }
}
