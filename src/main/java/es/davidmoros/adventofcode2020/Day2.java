package es.davidmoros.adventofcode2020;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class Day2 extends Day {

    private List<Pair<String,String>> inputLines;

    @Override
    public int numberDay() {
        return 2;
    }

    @Override
    public String inputResourceName() {
        return "day2.input";
    }

    @PostConstruct
    private void init() {
        inputLines = inputResourceLines().stream().parallel()
            .map(line -> line.split(": "))
            .map(lineSplitted -> Pair.of(lineSplitted[0], lineSplitted[1]))
            .collect(Collectors.toList());
    }

    @Override
    public String part1() {
        long numPasswordsOK = inputLines.stream().parallel()
            .filter(pair -> this.validatePasswordPart1(pair.getFirst(), pair.getSecond()))
            .count();

        return Long.toString(numPasswordsOK);
    }

    @Override
    public String part2() {
        long numPasswordsOK = inputLines.stream().parallel()
            .filter(pair -> validatePasswordPart2(pair.getFirst(), pair.getSecond()))
            .count();

        return Long.toString(numPasswordsOK);
    }   

    private boolean validatePasswordPart1 (String policy, String password) {
        String[] split = policy.split(" ");
        String[] lowHigh = split[0].split("-");
        String low = lowHigh[0];
        String high = lowHigh[1];
        String letter = split[1];

        String regexValidation = String.format("^([^%s]*%s[^%s]*){%s,%s}$", letter, letter, letter, low, high);
        
        return password.matches(regexValidation);
    }

    private boolean validatePasswordPart2 (String policy, String password) {
        String[] split = policy.split(" ");
        String[] positions = split[0].split("-");
        String position1 = positions[0];
        String position2 = positions[1];
        String letter = split[1];

        String regexValidationPos1 = String.format("^.{%s}%s.*", Integer.parseInt(position1)-1, letter);
        String regexValidationPos2 = String.format("^.{%s}%s.*", Integer.parseInt(position2)-1, letter);
        
        return password.matches(regexValidationPos1) ^ password.matches(regexValidationPos2);
    }     
}