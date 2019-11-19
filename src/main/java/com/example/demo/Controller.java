package com.example.demo;

import biweekly.ICalVersion;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.text.ICalWriter;
import biweekly.property.CalendarScale;
import biweekly.property.Method;
import biweekly.property.Status;

import biweekly.property.Uid;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class Controller {


    @GetMapping(value = "/users/{name}")
    public ResponseEntity<String> getICSFileHolidays(@PathVariable String name) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://adm.edu.p.lodz.pl/user/users.php?search=";

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search="+name).get();

        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + name, String.class);

        return response;
    }
}
