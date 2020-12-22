package es.davidmoros.adventofcode2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
public class Day7 extends Day {

    private static final String COLOR = "shiny gold";

    @Override
    public int numberDay() {
        return 7;
    }

    @Override
    public String inputResourceName() {
        return "day7.input";
    }

    @Override
    public String part1() {
        List<List<String>> inputLines = inputResourceLines().stream().parallel()
            .map(line -> line.replaceAll(" bags contain no other bags\\.", ""))
            .map(line -> line.replaceAll(" bag(s)?\\.", ""))
            .map(line -> line.replaceAll(" bag(s)?, \\d ", "#"))
            .map(line -> line.replaceAll(" bag(s)? contain \\d ", "#"))
            .map(line -> Arrays.asList(line.split("#")))
            .collect(Collectors.toList());

        List<String> colorBags = new ArrayList<>();

        for (List<String> paquete : inputLines) {
            if (paquete.contains(COLOR) && !paquete.get(0).equals(COLOR)) {
                colorBags.add(paquete.get(0));
            }
        }

        int colorBagsAddInIter = colorBags.size();
        while (colorBagsAddInIter > 0) {
            int colorBagsPrev = colorBags.size();
            for (List<String> paquete : inputLines) {
                String color0 = paquete.get(0);
                for (int i=1; i<paquete.size(); i++) {
                    String colorBolsa = paquete.get(i);
                    if (colorBags.contains(colorBolsa) && !colorBags.contains(color0)) {
                        colorBags.add(color0);
                        break;
                    }
                }
            }
            colorBagsAddInIter = colorBags.size() - colorBagsPrev;
        }

        return Integer.toString(colorBags.size());
    }

    @Override
    public String part2() {
        List<List<String>> inputLines = inputResourceLines().stream().parallel()
            .map(line -> line.replaceAll(" bags contain no other bags\\.", ""))
            .map(line -> line.replaceAll(" bag(s)?\\.", ""))
            .map(line -> line.replaceAll(" bag(s)?, ", "@"))
            .map(line -> line.replaceAll(" bag(s)? contain ", "@"))
            .map(line -> line.replaceAll("(\\d) ", "$1#"))
            .map(line -> Arrays.asList(line.split("@")))
            .collect(Collectors.toList());
        
        Map<String, List<Pair<Integer, String>>> colorBags = new HashMap<>();

        for (List<String> paquete : inputLines) {
            if (paquete.get(0).equals(COLOR)) {
                List<Pair<Integer, String>> bolsaCOLOR = new ArrayList<>();
                for (int i=1; i<paquete.size(); i++) {
					String[] bolsaSplit = paquete.get(i).split("#");
                    Integer numBolsas = Integer.valueOf(bolsaSplit[0]);
                    String colorBolsa = bolsaSplit[1];
                    bolsaCOLOR.add(Pair.of(numBolsas, colorBolsa));

                    colorBags.put(colorBolsa, null);
                }
                colorBags.put(COLOR, bolsaCOLOR);
                break;
            }
        }
        
        int colorBagsAddInIter = colorBags.size();
        while (colorBagsAddInIter > 0) {
            int colorBagsPrev = colorBags.size();
            for (List<String> paquete : inputLines) {
                String color0 = paquete.get(0);
                if (colorBags.containsKey(color0) && colorBags.get(color0) == null) {
                    List<Pair<Integer, String>> bolsaColor = new ArrayList<>();
                    colorBags.put(color0, bolsaColor);

                    for (int i=1; i<paquete.size(); i++) {
                        String[] bolsaSplit = paquete.get(i).split("#");
                        Integer numBolsas = Integer.valueOf(bolsaSplit[0]);
                        String colorBolsa = bolsaSplit[1];
                        bolsaColor.add(Pair.of(numBolsas, colorBolsa));

                        if (!colorBags.containsKey(colorBolsa)) {
                            colorBags.put(colorBolsa, null);
                        }
                    }
                }
            }
            colorBagsAddInIter = colorBags.size() - colorBagsPrev;
        }

        return Long.toString(numContainedBags(colorBags, COLOR));
    } 

    public long numContainedBags (Map<String, List<Pair<Integer, String>>> colorBags, String color) {
        long numContainedBags = 0;
        for (Pair<Integer, String> pair : colorBags.get(color)) {
            numContainedBags += pair.getLeft() + pair.getLeft()*numContainedBags(colorBags, pair.getRight());
        }

        return numContainedBags;
    }
}