package com.example.demo;

import biweekly.ICalVersion;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.text.ICalWriter;
import biweekly.property.CalendarScale;
import biweekly.property.Method;
import biweekly.property.Status;

import biweekly.property.Uid;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @ResponseStatus(code = HttpStatus.OK)
    public String getICSFileHolidays(@PathVariable String name) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://adm.edu.p.lodz.pl/user/users.php?search=";
        StringBuffer buffer = new StringBuffer();

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search="+name).get();
        Elements pracownicy = doc.select("div.user-info");

        for(Element element: pracownicy)
        {
            buffer.append(element.toString());
            Elements e = element.select("a");
            String s = e.get(0).attributes().get("title");

            VCard vcard = new VCard();
            StructuredName n = new StructuredName();
            n.setFamily(s.split(" ")[0]);
            n.setGiven(s.split(" ")[1]);
            n.getPrefixes().add("Mr");
            vcard.setStructuredName(n);
            vcard.setFormattedName(s);
            String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();

            buffer.append("<a href='vcard/"+ s +"'><button>Click Here</button></a>");
        }

        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + name, String.class);

        return buffer.toString();
    }

    @GetMapping(value = "/users/vcard/{name22}")
    @ResponseStatus(code = HttpStatus.OK)
    public String getICSFileHoliday2s(@PathVariable String name22) throws IOException {
            VCard vcard = new VCard();
            StructuredName n = new StructuredName();
            n.setFamily(name22.split(" ")[0]);
            n.setGiven(name22.split(" ")[1]);
            n.getPrefixes().add("Mr");
            vcard.setStructuredName(n);
            vcard.setFormattedName(name22);
            String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();

        return str;
    }
}
