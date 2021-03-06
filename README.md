# Ohjelmistotekniikan harjoitustyö

Kevään 2020 ohjelmistotekniikka-kurssi.

# Kevytlaskutus-sovellus

Sovelluksen avulla freelancerit ja pienyrittäjät voivat hallita yhden tai useamman yrityksensä asiakakkaita, tuotteita ja palveluita, sekä laskutustietoja. Sovellus ei vaadi rekisteröitymistä.

## Dokumentaatio

- [Loppupalautus -release](https://github.com/ilkkamaksy/ot-harjoitustyo/releases/tag/loppupalautus)
- [Viikko 6 -release](https://github.com/ilkkamaksy/ot-harjoitustyo/releases/tag/viikko6)
- [Viikko 5 -release](https://github.com/ilkkamaksy/ot-harjoitustyo/releases/tag/viikko5)
- [Vaatimusmäärittely](/documentation/vaatimusmaarittely.md)
- [Työaikakirjanpito](/documentation/tuntikirjanpito.md)
- [Arkkitehtuurikuvaus](/documentation/arkkitehtuuri.md)
- [Käyttöohje](/documentation/kayttajaohje.md)
- [Testausdokumentti](/documentation/testausdokumentti.md)

## Komentorivitoiminnot

### Ohjelman suoritus

Ohjelma voidaan käynnistää komennolla

`mvn compile exec:java -e -Dexec.mainClass=kevytlaskutus.Main` 

### Testaus

Testit suoritetaan komennolla

`mvn test`

Testikattavuusraportti luodaan komennolla

`mvn jacoco:report`

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi

Komento

`mvn package`

generoi hakemistoon target suoritettavan jar-tiedoston Kevytlaskutus-1.0-SNAPSHOT.jar

### JavaDoc

JavaDoc generoidaan komennolla

`mvn javadoc:javadoc`

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

`mvn jxr:jxr checkstyle:checkstyle`

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html