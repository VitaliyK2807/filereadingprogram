package org.example;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String PATH_INCOMING_FILES = "incomingfiles/";
    private static final String DEFAULT_SAVE_PATH = "out/";
    private static final String FILE_NAME_INTEGERS = "integers.txt";
    private static final String FILE_NAME_FLOATS = "floats.txt";
    private static final String FILE_NAME_STRINGS = "strings.txt";

    private static List<String> integerList;
    private static List<String> floatsList;
    private static  List<String> stringList;
    public static void main(String[] args) {
        String[] arg = {"-s", "-a", "in1.txt", "in2.txt"};
        List<String> pathList = getPathIncomingFiles(arg);

        if (pathList.isEmpty()) {
            System.out.println("Incoming files not found!");
            return;
        }

        FileProcessing fileProcessing = new FileProcessing(pathList);
        fileProcessing.startReadFiles();

        integerList = fileProcessing.getIntegerList();
        floatsList = fileProcessing.getFloatsList();
        stringList = fileProcessing.getStringList();

        readingOptions(arg);

    }

    private static void readingOptions(String[] args) {
        String newPath = "";
        String prefix = "";
        boolean briefStatistics = false;
        boolean fullStatistics = false;
        boolean addingFiles = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s" -> briefStatistics = true;
                case "-f" -> fullStatistics = true;
                case "-o" -> {
                    if (i + 1 < args.length) {
                        newPath = newPathTest(args[i+1]) ? args[i+1] : DEFAULT_SAVE_PATH;
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length) {
                        prefix = prefixTest(args[i+1]) ? args[i+1] : "";
                    }
                }
                case "-a" -> addingFiles = true;
            }
        }
        if (newPath.isEmpty()) {
            newPath = DEFAULT_SAVE_PATH;
        }
        WritingFile writingFile = new WritingFile(newPath, prefix, addingFiles);
        writingFile.writingFile(integerList, FILE_NAME_INTEGERS);
        writingFile.writingFile(floatsList, FILE_NAME_FLOATS);
        writingFile.writingFile(stringList, FILE_NAME_STRINGS);

        List<String> pathList = new ArrayList<>(List.of(
                newPath + FILE_NAME_INTEGERS,
                newPath + FILE_NAME_FLOATS,
                newPath + FILE_NAME_STRINGS));
        FileProcessing fileProcessing = new FileProcessing(pathList);
        StatisticsCalculation statisticsCalculation
                = new StatisticsCalculation(fileProcessing.getIntegerList(),
                fileProcessing.getFloatsList(),
                fileProcessing.getStringList());

        if (briefStatistics) {
            statisticsCalculation.briefStatistics();
        }

        if (fullStatistics) {
            statisticsCalculation.fullStatistics();
        }

    }

    private static boolean prefixTest(String string) {
        String regex = "[a-z1-9-_]+";
        return string.matches(regex);
    }

    private static boolean newPathTest(String string) {
        String regex = "[a-z1-9-/]+";
        return string.matches(regex);
    }

    private static List<String> getPathIncomingFiles(String[] args) {
        String regex = "[a-z1-9]+.txt";
        List<String> pathList = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches(regex)) {
                pathList.add(PATH_INCOMING_FILES + args[i]);
            }
        }
        return pathList;
    }

}
