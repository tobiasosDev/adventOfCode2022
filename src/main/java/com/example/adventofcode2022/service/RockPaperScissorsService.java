package com.example.adventofcode2022.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RockPaperScissorsService {
    private TextFileLoaderService textFileLoaderService;

    public int getTotalScore(int strategy) {
        List<String> linesOfRockPaperScissors = textFileLoaderService.loadTextFileAsListByNewLine("paperRockGame.txt");
        // Sum every line separated by empty line
        return linesOfRockPaperScissors.stream().map(line -> getScore(line, strategy)).mapToInt(Integer::intValue).sum();
    }

    private int getScore(String paperRockScissorLine, int strategy) {
        return winningAmountByChoosen(paperRockScissorLine, strategy) + paperRockScissorMapper(paperRockScissorLine, strategy);
    }

    private int winningAmountByChoosen(String paperRockScissor, int strategy) {
        String[] paperRockScissorArray = paperRockScissor.split(" ");
        String me = strategy == 1 ? mapToSame(paperRockScissorArray[1]): mapToSameByChange(paperRockScissorArray[1], paperRockScissorArray[0]);
        if (me.equals("A")) {
            return 1;
        }
        if (me.equals("B")) {
            return 2;
        }
        if (me.equals("C")) {
            return 3;
        }
        return 0;
    }

    //rock = A
    //paper = B
    //scissors = C
    private int paperRockScissorMapper(String paperRockScissor, int strategy) {
        String[] paperRockScissorArray = paperRockScissor.split(" ");
        String opponent = paperRockScissorArray[0];
        String me = strategy == 1 ? mapToSame(paperRockScissorArray[1]): mapToSameByChange(paperRockScissorArray[1], paperRockScissorArray[0]);
        if (me.equals(opponent)) {
            return 3;
        } else if (me.equals("B") && opponent.equals("A")) {
            return 6;
        } else if (me.equals("A") && opponent.equals("C")) {
            return 6;
        } else if (me.equals("C") && opponent.equals("B")) {
            return 6;
        } else {
            return 0;
        }
    }

    private String mapToSame(String paperRockScissor) {
        if (paperRockScissor.equals("Z")) {
            return "C";
        } else if (paperRockScissor.equals("X")) {
            return "A";
        } else if (paperRockScissor.equals("Y")) {
            return "B";
        } else {
            return "error";
        }
    }

    private String mapToSameByChange(String paperRockScissor, String opponent) {
        // Draw
        if (paperRockScissor.equals("Y")) {
            return opponent;
        }
        // Loose
        if (paperRockScissor.equals("X")) {
            if (opponent.equals("A")) {
                return "C";
            } else if (opponent.equals("B")) {
                return "A";
            } else if (opponent.equals("C")) {
                return "B";
            }
        }
        // Win
        if (paperRockScissor.equals("Z")) {
            if (opponent.equals("A")) {
                return "B";
            } else if (opponent.equals("B")) {
                return "C";
            } else if (opponent.equals("C")) {
                return "A";
            }
        }
        return "error";
    }
}
