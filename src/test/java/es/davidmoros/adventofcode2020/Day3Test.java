package es.davidmoros.adventofcode2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Day3Test implements DayTest{

    @Autowired
    private Day3 day;

    @Test
    public void part1() {
        assertEquals("265", day.part1());
    }

    @Test
    public void part2() {
        assertEquals("3154761400", day.part2());
    }
}