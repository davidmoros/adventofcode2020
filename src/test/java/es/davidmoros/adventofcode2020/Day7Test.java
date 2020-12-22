package es.davidmoros.adventofcode2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Day7Test implements DayTest {

    @Autowired
    private Day7 day;

    @Test
    public void part1() {
        assertEquals("161", day.part1());
    }

    @Test
    public void part2() {
        assertEquals("30899", day.part2());
    }
}