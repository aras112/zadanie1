package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class Reverse {

    @GetMapping(value = "/{string}")
    String reverse(@PathVariable String string){
        return StringUtils.reverse(string);
    }

}
