package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping(value = "/{string}")
    String reverse(@PathVariable String string){

        return StringUtils.reverse(string);
    }

}
