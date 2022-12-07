package com.example.adventofcode2022.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampCleanupService {

    private TextFileLoaderService textFileLoaderService;

    public int sumOfOverlappingCleanups(boolean fullCleanup) {
        List<String> linesOfCleanups = textFileLoaderService.loadTextFileAsListByNewLine("cleaningPlan.txt");
        if (fullCleanup) {
            return linesOfCleanups.stream().map(this::isFullyOverlapping).map(value -> value ? 1 : 0).mapToInt(Integer::intValue).sum();
        } else {
            return linesOfCleanups.stream().map(this::isPartiallyOverlapping).map(value -> value ? 1 : 0).mapToInt(Integer::intValue).sum();
        }
    }

    private boolean isFullyOverlapping(String line) {
        List<String> splittedLine = List.of(line.split(","));
        String[] firstElf = splittedLine.get(0).split("-");
        String[] secondElf = splittedLine.get(1).split("-");
        int firstElfStart = Integer.parseInt(firstElf[0]);
        int firstElfEnd = Integer.parseInt(firstElf[1]);
        int secondElfStart = Integer.parseInt(secondElf[0]);
        int secondElfEnd = Integer.parseInt(secondElf[1]);
        return firstElfStart <= secondElfStart && firstElfEnd >= secondElfEnd || secondElfStart <= firstElfStart && secondElfEnd >= firstElfEnd;
    }

    private boolean isPartiallyOverlapping(String line) {
        List<String> splittedLine = List.of(line.split(","));
        String[] firstElf = splittedLine.get(0).split("-");
        String[] secondElf = splittedLine.get(1).split("-");
        int firstElfStart = Integer.parseInt(firstElf[0]);
        int firstElfEnd = Integer.parseInt(firstElf[1]);
        int secondElfStart = Integer.parseInt(secondElf[0]);
        int secondElfEnd = Integer.parseInt(secondElf[1]);
        return firstElfStart <= secondElfStart && firstElfEnd >= secondElfStart || secondElfStart <= firstElfStart && secondElfEnd >= firstElfStart;
    }
}
