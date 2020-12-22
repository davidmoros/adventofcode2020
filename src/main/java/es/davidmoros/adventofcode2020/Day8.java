package es.davidmoros.adventofcode2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Day8 extends Day {

    @Override
    public int numberDay() {
        return 8;
    }

    @Override
    public String inputResourceName() {
        return "day8.input";
    }

    @Override
    public String part1() {
        long accumulator = 0;

        List<Integer> executedLines = new ArrayList<>();
        int executedLine = 0;
        int totalInstructions = inputResourceLines().size();
        while (executedLine != totalInstructions) {
            if (executedLines.contains(executedLine)) {
                break;
            }
            executedLines.add(executedLine);

            String[] instructionLine = inputResourceLines().get(executedLine).split(" ");
            String instruction = instructionLine[0];
            int value = Integer.valueOf(instructionLine[1]);
            switch (instruction ) {
                case "nop":
                    executedLine++;
                    break;
                case "acc":
                    accumulator += value;
                    executedLine++;
                    break;
                case "jmp":
                    executedLine += value;
                    break;
                default:
                    throw new PartException ("Unrecognized instruction.");
            }
        }

        if (executedLine == totalInstructions) {
            throw new PartException ("Infinite loop not found.");
        } else {
            return Long.toString(accumulator);
        }
    }

    @Override
    public String part2() {

        return Long.toString(0);
    }

    private boolean runProgram (String question, List<String> answers) {
        boolean allAnswerYesToSameQuestion = true;
        for (int j=1; j<answers.size(); j++) {
            if (!answers.get(j).contains(question)) {
                allAnswerYesToSameQuestion = false;
            }
        }

        return allAnswerYesToSameQuestion;
    }
}