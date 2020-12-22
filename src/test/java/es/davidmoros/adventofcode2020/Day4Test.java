package es.davidmoros.adventofcode2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Day4Test implements DayTest{

    @Autowired
    private Day4 day;

    @Test
    public void part1() {
        assertEquals("216", day.part1());
    }

    @Test
    public void part2() {
        assertEquals("150", day.part2());
    }
}