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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class Controller {


    @GetMapping("/holidays")
    public ResponseEntity<Resource> getICSFileHolidays() throws Exception {
        Document doc = Jsoup.connect("http://www.weeia.p.lodz.pl/").get();
        Elements swieta = doc.select("a.active");
        Elements opis = doc.select("div.InnerBox");
        ICalendar ical = new ICalendar();

        setIcal(ical);

        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int i = 0;


        for (Element e : swieta) {

            VEvent event = new VEvent();
            event.setSequence(0);
            event.addAttendee("aras112@o2.pl");
            event.setSummary("summary");
            event.setCreated(new Date(currentYear, currentMonth, Integer.parseInt(e.text()), 8, 0, 0));
            event.setDateTimeStamp(new Date());
            event.setDateStart(new Date(currentYear, currentMonth, Integer.parseInt(e.text()), 8, 0, 0));
            event.setDateEnd(new Date(currentYear, currentMonth, Integer.parseInt(e.text()), 20, 0, 0));
            event.setStatus(Status.confirmed());
            event.setUid(Uid.random());
            event.setDescription(opis.get(i).text());
            ical.addEvent(event);
            i++;
        }

        File file = new File("call.ics");
        ICalWriter writer = null;
        try {
            writer = new ICalWriter(file, ICalVersion.V2_0);
            writer.write(ical);
        } finally {
            if (writer != null) writer.close();
        }


        Resource resource = loadAsResource("call.ics");
        return new ResponseEntity<Resource>(resource, null, HttpStatus.OK);
    }

    private void setIcal(ICalendar ical) {
        ical.setDescription("Ważne wydarzenia");
        ical.setCalendarScale(new CalendarScale("GREGORIAN"));
        ical.setName("WEEIA CALENDAR");
        ical.setMethod(Method.REQUEST);
        ical.setCalendarScale(CalendarScale.gregorian());
    }


    private Resource loadAsResource(String filename) throws FileNotFoundException, MalformedURLException {
        try {
            Path file = Paths.get(filename);
            org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return (Resource) resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException e) {

            throw new FileNotFoundException();
        }
    }

}
