package es.davidmoros.adventofcode2020;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

public abstract class Day {

    private List<String> inputResourceLines;
    private List<String> inputResourceUnifiedLines;

    public abstract int numberDay();
    public abstract String inputResourceName();
    public abstract String part1();
    public abstract String part2();

    @PostConstruct
    private void postConstruct() {
        try (Stream<String> lines = Files.lines(Paths.get(inputResourceURI()))) {
            inputResourceLines = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new PartException(e.getMessage(), e);
        }
    }    

    private URI inputResourceURI() {
        try {
            return ClassLoader.getSystemResource(inputResourceName()).toURI();
        } catch (URISyntaxException e) {
            throw new PartException(e.getMessage(), e);
        }
    }

    protected List<String> inputResourceLines() {
        return inputResourceLines;
    }

    protected List<String> inputResourceUnifiedLines() {
        if (inputResourceUnifiedLines != null) {
            return inputResourceUnifiedLines;
        }

        inputResourceUnifiedLines = new ArrayList<>();
        int indexToAdd = 0;
        for (String line : inputResourceLines()) {
            if (StringUtils.isBlank(line)) {
                indexToAdd++;
            } else {
                if (indexToAdd == inputResourceUnifiedLines.size()) {
                    inputResourceUnifiedLines.add(line);
                } else {
                    inputResourceUnifiedLines.set(indexToAdd, inputResourceUnifiedLines.get(indexToAdd) + " " + line);
                }
            }
        }

        return inputResourceUnifiedLines;
    }
}