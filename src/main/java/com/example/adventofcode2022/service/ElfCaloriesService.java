package com.example.adventofcode2022.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElfCaloriesService {
    private TextFileLoaderService textFileLoaderService;

    public int getHighestCalories() {
        return parseCaloriesByString().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    public int getSumOfTopThreeOfListCalories() {
        return parseCaloriesByString().stream()
                .mapToInt(Integer::intValue)
                .map(i -> -i).sorted().map(i -> -i)
                .limit(3)
                .sum();

    }

    private List<Integer> parseCaloriesByString() {
        String caloriesSeparatedByEmptyLine = textFileLoaderService.loadTextFile("caloriesElf.txt");
        // Sum every line separated by empty line
        var linesOfCalories = Arrays.stream(caloriesSeparatedByEmptyLine.split("\\r?\\n\\r?\\n")).toList();
        List<Integer> summedElfCalories = linesOfCalories.stream()
                .map(line -> Arrays.stream(line.split("\\r?\\n"))
                        .mapToInt(Integer::parseInt)
                        .sum())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return summedElfCalories;
    }
}
