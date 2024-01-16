package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessing {
    private List<String> integerList;
    private List<String> floatsList;
    private List<String> stringList;
    private List<String> pathList;
    private List<List<String>> listOfReadData;

    public FileProcessing(List<String> pathList) {
        this.pathList = pathList;
        integerList = new ArrayList<>();
        floatsList = new ArrayList<>();
        stringList = new ArrayList<>();
        listOfReadData = new ArrayList<>();
    }
    public void startReadFiles() {
        pathList.forEach(path -> listOfReadData.add(readFiles(path)));
        listOfReadData.forEach(this::checkingAndRecordingData);
    }

    private List<String> readFiles(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"))){
            for(;;) {
                String line = buffer.readLine();
                if (line == null) {
                    break;
                }
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return List.of();
        }
        if (list.isEmpty()) {
            System.out.println(path + " - empty file!");
            return List.of();
        }
        return list;
    }

    private void checkingAndRecordingData(List<String> data) {
        if (!data.isEmpty()) {
            data.forEach(this::checkingString);
        }
    }

    private void checkingString(String string) {
        if (isNumberInteger(string)) {
            integerList.add(string);
            return;
        }
        if (isNumberLong(string)) {
            integerList.add(string);
            return;
        }
        if (isNumberFloat(string)) {
            floatsList.add(string);
            return;
        }
        stringList.add(string);
    }

    private boolean isNumberFloat(String string) {
        try {
            Float.valueOf(string);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    private boolean isNumberInteger(String string) {
        try {
            Integer.valueOf(string);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    private boolean isNumberLong(String string) {
        try {
            Long.valueOf(string);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public List<String> getIntegerList() {
        return new ArrayList<>(integerList);
    }

    public List<String> getFloatsList() {
        return new ArrayList<>(floatsList);
    }

    public List<String> getStringList() {
        return new ArrayList<>(stringList);
    }
}
