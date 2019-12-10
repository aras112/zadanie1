package com.example.demo;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;

import org.springframework.http.HttpHeaders;
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

import javax.activation.MimeType;

@RestController
public class Controller
    {


    private String script;

    @GetMapping(value = "/users/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public String getICSFileHolidays(@PathVariable String name) throws IOException
        {
        StringBuffer buffer = new StringBuffer();

        script = "<!-- Latest compiled and minified CSS -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\n" +
                "\n" +
                "<!-- jQuery library -->\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                "\n" +
                "<!-- Latest compiled JavaScript -->\n" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min" +
                ".js\"></script>\n";

        String style = "<style>\n" +
                "\n" +
                "body{\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                ".user-info{\n" +
                "\n" +
                "  min-width: 600px;\n" +
                "  width: min-content;\n" +
                "  display: block;\n" +
                "  margin: 20px auto;\n" +
                "  color: grey;\n" +
                "  padding: 20px;\n" +
                "  border: solid black;\n" +
                "  border-radius: 20px;\n" +
                "  background: rgb(2,0,36);\n" +
                "  background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 35%, rgba(0,212,255,1) 100%);\n" +
                "}\n" +
                "\n" +
                "a{\n" +
                "  margin: 1%;\n" +
                "  color: grey;\n" +
                "}</style>";
        buffer.append(script);
        buffer.append(style);

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search=" + name).get();
        Elements pracownicy = doc.select("div.user-info");

        for (Element element : pracownicy)
            {
            Elements e = element.select("a");
            String s = e.get(0).attributes().get("title");
            element.append("<a class=\"fullprofile-link btn btn-default\" href='vcard/" + s +
                    "'>vCard</a>");
            buffer.append(element.toString());
            }

        return buffer.toString();
        }

    @GetMapping("")
    public String getInfo(){
    return "<article class=\"markdown-body entry-content p-5\" itemprop=\"text\"><h2><a id=\"user-content-dokumentacja\" class=\"anchor\" aria-hidden=\"true\" href=\"#dokumentacja\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a><strong>Dokumentacja</strong></h2>\n" +
            "<p>Mobilny generator vCard WEEIA umożliwia wyszukanie użytkowników i generowanie vCard.</p>\n" +
            "<ul>\n" +
            "<li>\n" +
            "<p><em><strong>Aplikacje na telefonie można testować pod adresem</strong></em> <a href=\"http://testwielokrotnym.herokuapp.com/\" rel=\"nofollow\">http://testwielokrotnym.herokuapp.com/</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p><strong>URL</strong></p>\n" +
            "<p><code>/users/{user_name}</code></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p><strong>Metoda</strong></p>\n" +
            "<p><code>GET</code></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p><strong>Parametry URL</strong></p>\n" +
            "</li>\n" +
            "</ul>\n" +
            "<p><code>{user_name}</code> - nazwa użytkownika systemu wikamp</p>\n" +
            "<ul>\n" +
            "<li>\n" +
            "<p><strong>Poprawna odpowiedz</strong></p>\n" +
            "<ul>\n" +
            "<li><strong>Poprawny kod odpowiedzi</strong> <code>200</code> <br></li>\n" +
            "<li><strong>Zawartość</strong> <code>STRONA HTML</code></li>\n" +
            "<li><strong>Przydładowy zawartość strony html</strong><br></li>\n" +
            "</ul>\n" +
            "</li>\n" +
            "</ul>\n" +
            "<div> \n" +
            " <a href=\"/aras112/zadanie1/blob/zadanie5_heroku/user/profile.php?id=3016\" title=\"Paweł Kapusta\"></a>\n" +
            " <h3><a id=\"user-content-paweł-kapusta\" class=\"anchor\" aria-hidden=\"true\" href=\"#paweł-kapusta\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a><a href=\"/aras112/zadanie1/blob/zadanie5_heroku/user/profile.php?id=3016\" title=\"Paweł Kapusta\">Paweł Kapusta</a></h3> \n" +
            " <h4><a id=\"user-content-dr-inż-\" class=\"anchor\" aria-hidden=\"true\" href=\"#dr-inż-\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>dr inż. </h4> \n" +
            " <div> \n" +
            "  <ul> \n" +
            "   <li> <span>Afiliacja do:</span> <span>I24 - Instytut Informatyki Stosowanej</span> </li> \n" +
            "  </ul> \n" +
            " </div> \n" +
            " <div>\n" +
            "  <a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://camo.githubusercontent.com/d23352969c1f9ce305ebee3d7a1635480e1d201f/68747470733a2f2f61646d2e6564752e702e6c6f647a2e706c2f7468656d652f696d6167652e7068702f616461707461626c652f636f72652f313537353335323332352f692f656d61696c\"><img src=\"https://camo.githubusercontent.com/d23352969c1f9ce305ebee3d7a1635480e1d201f/68747470733a2f2f61646d2e6564752e702e6c6f647a2e706c2f7468656d652f696d6167652e7068702f616461707461626c652f636f72652f313537353335323332352f692f656d61696c\" alt=\"\" data-canonical-src=\"https://adm.edu.p.lodz.pl/theme/image.php/adaptable/core/1575352325/i/email\" style=\"max-width:100%;\"></a> \n" +
            "  <a href=\"/aras112/zadanie1/blob/zadanie5_heroku/user/usermailform.php?user=3016\">Wyślij wiadomość</a>\n" +
            " </div> \n" +
            " <a href=\"https://adm.edu.p.lodz.pl/user/view.php?id=3016\" rel=\"nofollow\">Profil publiczny</a>\n" +
            " <a href=\"https://adm.edu.p.lodz.pl/user/fullprofile.php?id=3016\" rel=\"nofollow\">Pełny profil</a> \n" +
            " <a href=\"/aras112/zadanie1/blob/zadanie5_heroku/vcard/Paweł Kapusta\">vCard</a>\n" +
            "</div>\n" +
            "</article>";
    }

    @GetMapping(value = "/users/vcard/{name22}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<byte[]> getICSFileHoliday2s(@PathVariable String name22) throws IOException
        {
        VCard vcard = new VCard();
        StructuredName n = new StructuredName();
        n.setFamily(name22.split(" ")[0]);
        n.setGiven(name22.split(" ")[1]);
        n.setLanguage("pl");
        n.getPrefixes().add("Mr");
        vcard.setStructuredName(n);
        vcard.setFormattedName(name22);
        String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + name22.replace(" ", "_")+".vcf");
        responseHeaders.add("Content-Type",MediaType.APPLICATION_OCTET_STREAM_VALUE);


        return new ResponseEntity<>(str.getBytes(),responseHeaders,HttpStatus.OK);
        }

    @GetMapping(value = "/user/profile.php")
    @ResponseStatus(code = HttpStatus.OK)
    public String redict(@RequestParam String id) throws IOException
        {
        return Jsoup.connect("https://adm.edu.p.lodz.pl/user/profile.php?id=" + id).get().html();
        }

    @GetMapping(value = "/user/usermailform.php")
    @ResponseStatus(code = HttpStatus.OK)
    public String redict2(@RequestParam String user) throws IOException
        {
        return Jsoup.connect("https://adm.edu.p.lodz.pl/user/usermailform.php?user=" + user).get().html();
        }
    }
