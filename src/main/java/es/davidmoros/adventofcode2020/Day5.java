package es.davidmoros.adventofcode2020;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Day5 extends Day {

    private List<String> inputLines;

    @Override
    public int numberDay() {
        return 5;
    }

    @Override
    public String inputResourceName() {
        return "day5.input";
    }

    @PostConstruct
    private void init() {
        inputLines = inputResourceLines().stream().parallel()
            .map(line -> line.replace('F', '0'))
            .map(line -> line.replace('B', '1'))
            .map(line -> line.replace('L', '0'))
            .map(line -> line.replace('R', '1'))
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public String part1() {
        String lastLine = inputLines.get(inputLines.size()-1);
        int highestSeatID = Integer.parseInt(lastLine.substring(0, 7), 2)*8 + Integer.parseInt(lastLine.substring(7, 10), 2);

        return Integer.toString(highestSeatID);
    }

    @Override
    public String part2() {
        List<Integer> seatIDs = inputLines.stream()
            .map(line -> Integer.parseInt(line.substring(0, 7), 2)*8 + Integer.parseInt(line.substring(7, 10), 2))
            .collect(Collectors.toList());

        for (int i=0; i<seatIDs.size()-1; i++) {
            Integer currentID = seatIDs.get(i);
            Integer postID = seatIDs.get(i+1);

            if (!Integer.valueOf(currentID+1).equals(postID)) {
                return Integer.toString(currentID+1);
            }
        }

        throw new PartException("Seat Id not found");
    }   
}