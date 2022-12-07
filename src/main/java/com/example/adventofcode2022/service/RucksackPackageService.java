package com.example.adventofcode2022.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RucksackPackageService {

    private TextFileLoaderService textFileLoaderService;

    public int sumOfPriorities() {
        List<String> rucksackPackages = textFileLoaderService.loadTextFileAsListByNewLine("rucksackItems.txt");
        return rucksackPackages.stream().map(this::splitAndFindDuplicate).map(this::priorityOfCharacter).mapToInt(Integer::intValue).sum();
    }

    public int sumOfPrioritiesByThreeLists() {
        List<String> rucksackPackages = textFileLoaderService.loadTextFileAsListByNewLine("rucksackItems.txt");
        var sum = 0;
        // Iterate over list, in groups of 3
        for (int i = 0; i < rucksackPackages.size(); i += 3) {
            List<String> subList = rucksackPackages.subList(i, i + 3);
            // Find duplicate
            Character duplicate = findDuplicateInThreeLists(subList.get(0), subList.get(1), subList.get(2));
            // Find priority
            int priority = priorityOfCharacter(duplicate);
            // Add to sum
            sum += priority;
        }
        return sum;
    }

    // find duplicate character in all three strings
    private Character findDuplicateInThreeLists(String first, String second, String third) {
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            if (second.contains(String.valueOf(c)) && third.contains(String.valueOf(c))) {
                return c;
            }
        }
        return null;
    }

    private Character splitAndFindDuplicate(String input) {
        String first = input.substring(0, input.length() / 2);
        String second = input.substring(input.length() / 2);
        for (int i = 0; i < first.length(); i++) {
            if (second.contains(first.substring(i, i + 1))) {
                return first.charAt(i);
            }
        }

        return null;
    }

    private int priorityOfCharacter(Character character) {
        //get index of character in alphabet
        String line = "abcdefghijklmnopqrstuvwxyz";

        int position = line.indexOf(String.valueOf(character).toLowerCase())+1;

        // check if uppercase or lowercase
        if (Character.isUpperCase(character)) {
            return position + 26;
        } else {
            return position;
        }
    }
}
