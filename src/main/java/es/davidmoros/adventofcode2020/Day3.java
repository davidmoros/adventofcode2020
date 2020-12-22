package es.davidmoros.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class Day3 extends Day {

    private static final char TREEMARK = '#';

    @Override
    public int numberDay() {
        return 3;
    }

    @Override
    public String inputResourceName() {
        return "day3.input";
    }

    @Override
    public String part1() {
        Pair<Integer,Integer> slope = Pair.of(3, 1);
        long numTrees = countTrees(slope);

        return Long.toString(numTrees);
    }

    @Override
    public String part2() {
        List<Pair<Integer,Integer>> slopes = new ArrayList<>();
        slopes.add(Pair.of(1,1));
        slopes.add(Pair.of(3,1));
        slopes.add(Pair.of(5,1));
        slopes.add(Pair.of(7,1));
        slopes.add(Pair.of(1,2));

        long multipliedNumTrees = 1;
        for (Pair<Integer,Integer> slope : slopes) {
            multipliedNumTrees = multipliedNumTrees * countTrees(slope);
        }

        return Long.toString(multipliedNumTrees);
    }

    private long countTrees (Pair<Integer,Integer> slope) {
        long numTrees = 0;

        int incXbyIter = slope.getFirst();
        int incYbyIter = slope.getSecond();

        int currentX = 0;
        for (int currentY = 0; currentY < inputResourceLines().size(); currentY=currentY+incYbyIter) {
            String line = inputResourceLines().get(currentY);
            char currentXmark = line.charAt(currentX);
            if (currentXmark == TREEMARK) {
                numTrees++;
            }
            currentX = (currentX + incXbyIter) % line.length();
        }

        return numTrees;
    }
}