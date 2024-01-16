package org.example;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class StatisticsCalculation {

    private List<String> integerList;
    private List<String> floatsList;
    private List<String> stringList;

    public StatisticsCalculation(List<String> integerList, List<String> floatsList, List<String> stringList) {
        this.integerList = integerList;
        this.floatsList = floatsList;
        this.stringList = stringList;
    }

    public void briefStatistics() {
        if (!integerList.isEmpty()) {
            delimiter();
            System.out.println("Count of elements in a file with integers :" + integerList.size());
        }
        if (!floatsList.isEmpty()) {
            delimiter();
            System.out.println("Count of elements in a file with float numbers :" + floatsList.size());
        }
        if (!stringList.isEmpty()) {
            delimiter();
            System.out.println("Count of elements in file with strings :" + stringList.size());
        }
    }

    public void fullStatistics() {
        if (!integerList.isEmpty()) {
            delimiter();
            System.out.println("File statistics with integers:");
            fullStatisticsOnIntegers();
        }
        if (!floatsList.isEmpty()) {
            delimiter();
            System.out.println("Float number file statistics:");
            fullStatisticsOnFloats();
        }
        if (!stringList.isEmpty()) {
            delimiter();
            System.out.println("String file statistics:");
            fullStatisticsOnStrings();
        }
    }

    private void fullStatisticsOnStrings() {
        int minValue = stringList.stream()
                .map(CharSequence::length)
                .min(Integer::compareTo)
                .get();
        int maxValue = stringList.stream()
                .map(CharSequence::length)
                .max(Integer::compareTo)
                .get();
        System.out.println("Minimum string: " + minValue);
        System.out.println("Maximum string: " + maxValue);
        System.out.println("Count of strings: " + stringList.size());
    }

    private void fullStatisticsOnFloats() {
        float minValue = floatsList.stream()
                .map(Float::valueOf)
                .min(Float::compareTo)
                .get();
        float maxValue = floatsList.stream()
                .map(Float::valueOf)
                .max(Float::compareTo)
                .get();
        double sumValue = floatsList.stream()
                .map(Float::valueOf)
                .flatMapToDouble(DoubleStream::of)
                .sum();
        OptionalDouble averageValue = floatsList.stream()
                .map(Float::valueOf)
                .mapToDouble(v -> v)
                .average();
        System.out.println("Minimum value: " + minValue);
        System.out.println("Maximum value: " + maxValue);
        System.out.println("Sum of numbers: " + String.format("%.4f", sumValue));
        System.out.println("Average value: " + String.format("%.4f", averageValue.getAsDouble()));
    }
    private void fullStatisticsOnIntegers() {
        long minValue = integerList.stream()
                .map(Long::valueOf)
                .min(Long::compareTo)
                .get();
        long maxValue = integerList.stream()
                .map(Long::valueOf)
                .max(Long::compareTo)
                .get();
        long sumValue = integerList.stream()
                .map(Long::valueOf)
                .flatMapToLong(LongStream::of)
                .sum();
        OptionalDouble averageValue = integerList.stream()
                .map(Long::valueOf)
                .mapToLong(v -> v)
                .average();
        System.out.println("Minimum value: " + minValue);
        System.out.println("Maximum value: " + maxValue);
        System.out.println("Sum of numbers: " + sumValue);
        System.out.println("Average value: " + String.format("%.2f", averageValue.getAsDouble()));
    }

    private void delimiter() {
        System.out.println("----------|--------------|-------------|-----------");
    }
}
