package es.davidmoros.adventofcode2020;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Adventofcode2020Application implements ApplicationRunner {

	@Autowired
	List<Day> days;

	public static void main(String[] args) {
		SpringApplication.run(Adventofcode2020Application.class, args);
	}

	@Override
    public void run(ApplicationArguments args) throws Exception {
		days.stream().forEach (day -> {
			log.info("Day {} part 1. Solution: {}", day.numberDay(), day.part1());
			log.info("Day {} part 2. Solution: {}", day.numberDay(), day.part2());
		});
    }
}
