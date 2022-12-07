package com.example.adventofcode2022.web;

import com.example.adventofcode2022.service.CampCleanupService;
import com.example.adventofcode2022.service.ElfCaloriesService;
import com.example.adventofcode2022.service.RockPaperScissorsService;
import com.example.adventofcode2022.service.RucksackPackageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdventOfCodeController {

    private ElfCaloriesService elfCaloriesService;

    private RockPaperScissorsService rockPaperScissorsService;

    private RucksackPackageService rucksackPackageService;

    private CampCleanupService campCleanupService;

    @GetMapping("/1/{version}")
    public String getDay1(@PathVariable String version) {
        if (version.equals("1")) {
            return "Result Day 1.1: " + elfCaloriesService.getHighestCalories();
        } else if (version.equals("2")) {
            return  "Result Day 1.2: " + elfCaloriesService.getSumOfTopThreeOfListCalories();
        } else {
            return "Day 1, version " + version;
        }
    }

    @GetMapping("/2/{version}")
    public String getDay2(@PathVariable String version) {
        if (version.equals("1")) {
            return "Result Day 2.1: " + rockPaperScissorsService.getTotalScore(1);
        } else if (version.equals("2")) {
            return  "Result Day 2.2: " + rockPaperScissorsService.getTotalScore(2);
        } else {
            return "Day 1, version " + version;
        }
    }

    @GetMapping("/3/{version}")
    public String getDay3(@PathVariable String version) {
        if (version.equals("1")) {
            return "Result Day 3.1: " + rucksackPackageService.sumOfPriorities();
        } else if (version.equals("2")) {
            return  "Result Day 3.2: " + rucksackPackageService.sumOfPrioritiesByThreeLists();
        } else {
            return "Day 1, version " + version;
        }
    }

    @GetMapping("/4/{version}")
    public String getDay4(@PathVariable String version) {
        if (version.equals("1")) {
            return "Result Day 4.1: " + campCleanupService.sumOfOverlappingCleanups(true);
        } else if (version.equals("2")) {
            return  "Result Day 4.2: " + campCleanupService.sumOfOverlappingCleanups(false);
        } else {
            return "Day 1, version " + version;
        }
    }

}
