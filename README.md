**Dokumentacja**
----
  Mobilny generator vCard WEEIA umożliwia wyszukanie użytkowników i generowanie vCard. 
 * ***Aplikacje na telefonie można testować pod adresem*** http://testwielokrotnym.herokuapp.com/

#Wyszukiwarka

* **URL**

  `/`

* **Metoda**

  `GET`

*  **Parametry URL**

  `brak`

* **Poprawna odpowiedz**

  * **Poprawny kod odpowiedzi** `200` <br />
   * **Zawartość** `STRONA HTML`

#Lista

* **URL**

  `/users/{user_name}`

* **Metoda**

  `GET`

*  **Parametry URL**

  `{user_name}` - nazwa użytkownika systemu wikamp

* **Poprawna odpowiedz**

  * **Poprawny kod odpowiedzi** `200` <br />
   * **Zawartość** `STRONA HTML`
   * **Przydładowy zawartość strony html**<br >

```html
<div class="user-info"> 
 <a href="/user/profile.php?id=3016" title="Paweł Kapusta"></a>
 <h3><a href="/user/profile.php?id=3016" title="Paweł Kapusta">Paweł Kapusta</a></h3> 
 <h4>dr inż. </h4> 
 <div class="extra-info"> 
  <ul> 
   <li> <span class="item-title">Afiliacja do:</span> <span class="item-content">I24 - Instytut Informatyki Stosowanej</span> </li> 
  </ul> 
 </div> 
 <div class="sendmail-link btn btn-default">
  <img class="smallicon" src="https://adm.edu.p.lodz.pl/theme/image.php/adaptable/core/1575352325/i/email" alt=""> 
  <a href="/user/usermailform.php?user=3016">Wyślij wiadomość</a>
 </div> 
 <a class="fullprofile-link btn btn-default" href="https://adm.edu.p.lodz.pl/user/view.php?id=3016">Profil publiczny</a>
 <a class="fullprofile-link btn btn-default" href="https://adm.edu.p.lodz.pl/user/fullprofile.php?id=3016">Pełny profil</a> 
 <a class="fullprofile-link btn btn-default" href="vcard/Paweł Kapusta">vCard</a>
</div>
```

#vCard

* **URL**

  `/user/vcard/{user_name}`

* **Metoda**

  `GET`

*  **Parametry URL**

  `{user_name}` - nazwa użytkownika systemu wikamp

* **Poprawna odpowiedz**

  * **Poprawny kod odpowiedzi** `200` <br />
   * **Zawartość** `File ivf`
   * **Przydładowy zawartość pliku**<br >
```text
BEGIN:VCARD
VERSION:4.0
PRODID:ez-vcard 0.10.5
N;LANGUAGE=pl:Paweł;Kapusta;;Mr;
ORG;AFILIACJA DO=I24 - Instytut Informatyki Stosowanej:
FN:Paweł Kapusta
END:VCARD
```

