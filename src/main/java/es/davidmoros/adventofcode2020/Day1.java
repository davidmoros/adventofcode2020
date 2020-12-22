package es.davidmoros.adventofcode2020;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Day1 extends Day {

    private List<Integer> inputLines;

    @Override
    public int numberDay() {
        return 1;
    }

    @Override
    public String inputResourceName() {
        return "day1.input";
    }

    @PostConstruct
    private void init() {
        inputLines = inputResourceLines().stream().parallel()
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public String part1() {
        for (int i=0; i<inputLines.size()-1; i++) {
            for (int j=1; j<inputLines.size(); j++) {
                Integer vali = inputLines.get(i);
                Integer valj = inputLines.get(j);
                if (Integer.valueOf(vali+valj).equals(2020)) {
                    return String.valueOf(vali*valj);
                }
            }
        }

        throw new PartException("None pair of numbers sum 2020");
    }

    @Override
    public String part2() {
        for (int i=0; i<inputLines.size()-2; i++) {
            for (int j=1; j<inputLines.size()-1; j++) {
                for (int k=2; k<inputLines.size(); k++) {
                    Integer vali = inputLines.get(i);
                    Integer valj = inputLines.get(j);
                    Integer valk = inputLines.get(k);
                    if (Integer.valueOf(vali+valj+valk).equals(2020)) {
                        return String.valueOf(vali*valj*valk);
                    }
                }
            }
        }

        throw new PartException("None trio of numbers sum 2020");
    }    
}