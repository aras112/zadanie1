package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class Reverse {

    private Pattern hasSpecialCase = Pattern.compile("[^A-Za-z0-9]");
    private Pattern hasNumber = Pattern.compile("[0-9]");

    @GetMapping(value = "isMixedCase/{string}")
    boolean isMixedCase(@PathVariable String string){
        return StringUtils.isMixedCase(string);
    }

    @GetMapping(value = "isUpperCase/{string}")
    boolean isUpperCase(@PathVariable String string){
        return StringUtils.isAllUpperCase(string);
    }

    @GetMapping(value = "isLowerCase/{string}")
    boolean isLowerCase(@PathVariable String string){
        return StringUtils.isAllLowerCase(string);
    }

    @GetMapping(value = "hasWhitespaceInString/{string}")
    boolean hasSpaceInString(@PathVariable String string){
        return StringUtils.containsWhitespace(string);
    }

    @GetMapping(value = "hasSpecialCase/{string}")
    boolean hasSpecialCase(@PathVariable String string){
        return hasSpecialCase.matcher(string).find();
    }

    @GetMapping(value = "isNumeric/{string}")
    boolean isNumeric(@PathVariable String string){
        return StringUtils.isNumeric(string);
    }

    @GetMapping(value = "hasNumber/{string}")
    boolean hasNumber(@PathVariable String string){
        return hasNumber.matcher(string).find();
    }

    @PostMapping(value = "/matchPattern")
    boolean matchPattern(@RequestBody PatternEntity patternEntity){
        return patternEntity.getNapis().contains(patternEntity.getCiag());
    }

}
