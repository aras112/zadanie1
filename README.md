Zadanie: Analiza tekstu pod katem tego czy sa male,duze litery,ma małe i duże litery czy ma znaki specjalne, czy ma spacje

Czy napis zawiera małe i duże liery?

Zapytanie typu GET na URL:
{nazwaHosta}/isMixedCase/{napis}

Odpowiedz: true lub false



Czy napis zawiera same duże litery?

Zapytanie typu GET na URL:
{nazwaHosta}/isUpperCase/{napis}

Odpowiedz: true lub false



Czy napis zawiera same małe litery? 

Zapytanie typu GET na URL:
{nazwaHosta}/isLowerCase/{napis}

Odpowiedz: true lub false



Czy napis zawiera spacje? 

Zapytanie typu GET na URL:
{nazwaHosta}/hasWhitespaceInString/{napis}

Odpowiedz: true lub false



Czy napis zawiera znaki specjalne litery? 

Zapytanie typu GET na URL:
{nazwaHosta}/hasSpecialCase/{napis}

Odpowiedz: true lub false



Czy napis jest licza? 

Zapytanie typu GET na URL:
{nazwaHosta}/isNumeric/{napis}

Odpowiedz: true lub false


Czy napis zawiera liczbe? 

Zapytanie typu GET na URL:
{nazwaHosta}/hasNumber/{napis}

Odpowiedz: true lub false



Czy napis zawiera podany ciag? 


Zapytanie w postaci JSON:
{
  "napis": "demoData",
  "ciag": "demoData"
}

wysłamy na URL typu POST:

{nazwaHosta}/matchPattern


Odpowiedz: true lub false
