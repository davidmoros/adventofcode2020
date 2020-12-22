package es.davidmoros.adventofcode2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Day6 extends Day {

    @Override
    public int numberDay() {
        return 6;
    }

    @Override
    public String inputResourceName() {
        return "day6.input";
    }

    @Override
    public String part1() {
        long sum = inputResourceUnifiedLines().stream().parallel()
            .mapToLong(line -> line.replace(" ", "").chars().distinct().count())
            .sum();

        return Long.toString(sum);
    }

    @Override
    public String part2() {
        List<List<String>> inputLines = inputResourceUnifiedLines().stream().parallel()
            .map(line -> Arrays.asList(line.split(" ")))
            .collect(Collectors.toList());

        long sum = 0;
        for (List<String> lineSplitted : inputLines) {
            String firstSplit = lineSplitted.get(0);
            for (int i=0; i<firstSplit.length(); i++) {
                String charAti = String.valueOf(firstSplit.charAt(i));
                if (allAnswerYesToSameQuestion(charAti, lineSplitted)) {
                    sum++;
                }

            }
        }

        return Long.toString(sum);
    }

    private boolean allAnswerYesToSameQuestion (String question, List<String> answers) {
        boolean allAnswerYesToSameQuestion = true;
        for (int j=1; j<answers.size(); j++) {
            if (!answers.get(j).contains(question)) {
                allAnswerYesToSameQuestion = false;
            }
        }

        return allAnswerYesToSameQuestion;
    }
}