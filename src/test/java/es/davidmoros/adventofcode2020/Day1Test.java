package es.davidmoros.adventofcode2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Day1Test implements DayTest{

    @Autowired
    private Day1 day;

    @Test
    public void part1() {
        assertEquals("800139", day.part1());
    }

    @Test
    public void part2() {
        assertEquals("59885340", day.part2());
    }    
}