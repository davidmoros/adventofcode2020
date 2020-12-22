package es.davidmoros.adventofcode2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
public class Day4 extends Day {

    @AllArgsConstructor
    private enum FIELDS_PASSPORT {
        BIRTH_YEAR ("byr", true, Pattern.compile("^(19[2-9][0-9]|200[0-2])$")),
        ISSUE_YEAR ("iyr", true, Pattern.compile("^(201[0-9]|2020)$")),
        EXPIRATION_YEAR("eyr", true, Pattern.compile("^(202[0-9]|2030)$")),
        HEIGHT ("hgt", true, Pattern.compile("^(((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in))$")),
        HAIR_COLOR ("hcl", true, Pattern.compile("^#[0-9a-f]{6}$")),
        EYE_COLOR ("ecl", true, Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$")),
        PASSPORT_ID ("pid", true, Pattern.compile("^\\d{9}$")),
        COUNTRY_ID ("cid", false, Pattern.compile("^.*$"));
    
        @Getter
        private String acro;
        @Getter
        private boolean mandatory;
        @Getter
        private Pattern regexValidation;
    }

    private List<Map<String,String>> inputLines = new ArrayList<>();

    @Override
    public int numberDay() {
        return 4;
    }

    @Override
    public String inputResourceName() {
        return "day4.input";
    }

    @PostConstruct
    private void init() {
        for (String line : inputResourceUnifiedLines()) {
            Map<String, String> map = new HashMap<>();
            List<String> splittedLine = Arrays.asList(line.split(" "));
            for (String splittedField : splittedLine) {
                String[] split = splittedField.split(":");
                map.put(split[0], split[1]);
            }
            inputLines.add(map);
        }
    }

    @Override
    public String part1() {
        long numPassportsOK = inputLines.stream().parallel()
            .filter(this::validatePassportPart1)
            .count();

        return Long.toString(numPassportsOK);
    }

    @Override
    public String part2() {
        long numPassportsOK = inputLines.stream().parallel()
            .filter(this::validatePassportPart2)
            .count();

        return Long.toString(numPassportsOK);        
    }   

    private boolean validatePassportPart1 (Map<String, String> fieldsPassport) {
        for (FIELDS_PASSPORT fieldPassport : Arrays.asList(FIELDS_PASSPORT.values())){
            if (fieldPassport.mandatory && !fieldsPassport.containsKey(fieldPassport.acro)) {
                return false;
            }
        }

        return true;
    }

    private boolean validatePassportPart2 (Map<String, String> fieldsPassport) {
        for (FIELDS_PASSPORT fieldPassport : Arrays.asList(FIELDS_PASSPORT.values())){
            if (fieldPassport.mandatory && (!fieldsPassport.containsKey(fieldPassport.acro) || !fieldPassport.regexValidation.matcher(fieldsPassport.get(fieldPassport.acro)).matches())) {
                return false;
            }
        }

        return true;
    }     
}