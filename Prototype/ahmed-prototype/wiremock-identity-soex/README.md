# Soex Identity Wiremock

Dit is project voor SOEX Identity Wiremock.

Omdat de cloud Identity mock op de orignele wiremockapi.cloud (zie onderstaande URL) bij gebruik door veel studenten tegelijkertijd snel over de limiet gaat, kun je met dit project lokaal een servertje draaien die response terug geeft.

Courtesy van minordevops.nl draait er ook gehoste versie op: `http://identity-wiremock.minordevops.nl:8080` (NB: nog geen SSL). Zo hoef je zelf lokaal niks te starten, zie voorbeeld curl aanroepen bij 1. En mocht de server down gaan, dan kun je met onderstaande stappenplan onder punt 2 de wiremock alsnog op je eigen computer draaien.

## 1. Voorbeeld aanroepen naar cloud versie

Voer op de terminal onderstaande commando's uit (uitgaande dat je [curl](https://help.ubidots.com/en/articles/2165289-learn-how-to-install-run-curl-on-windows-macosx-linux) hebt geinstalleerd):

```console
curl -X POST "http://identity-wiremock.minordevops.nl:8080/login" -H "Content-Type: application/json" -d '{"username": "edevries", "password": "3g2Rw9sT1x"}'
```

```console
curl -X POST "http://identity-wiremock.minordevops.nl:8080/checkAppAccess?token=qidl6dxxtzhlm5ya" -H "Content-Type: application/json" -d '{"username": "edevries", "application": "triptop"}'
```

Originele server: [https://triptop-identity.wiremockapi.cloud/login](https://triptop-identity.wiremockapi.cloud/login)

## 2. Stappenplan zelf runnen

1. Installeer [Docker Desktop](https://www.docker.com/products/docker-desktop/) als je deze nog niet hebt. Er is een client voor Windows of macOS. Als je Linux hebt, heb je het vast al geinstalleerd.

2. Start een terminal op macOS of Windows en clone dit project met:

```console
git clone https://aim-ene/wiremock-identity-soex
```

En ga naar deze folder met:

```console
cd wiremock-identity-soex
```

3. Run de wiremock server als Docker container lokaal met:

```console
docker compose up -d
```

NB De `-d` optie hierbij start deze op in `detached` mode, zodat je in dezelfde terminal andere commando's kan tikken, zoals de `curl` commando's i.p.v. stoppen met `ctrl+c`. Mocht je de container in detached mode willen stoppen dan doe je `docker compose down` in de huidige folder.

4. Nu runt de server op poort 8080. Als hier al iets anders draait em het voorgaande commandoe niet werken gebruik dan een ander poort nr door deze in te stellen in de `docker-compose.yaml`, zoals `8090`. En vervang poortnummer ook in onderstaande commando's.

Stuur als test met `curl` een request:

```console
curl -X POST "http://localhost:8080/login" -H "Content-Type: application/json" -d '{"username": "edevries", "password": "3g2Rw9sT1x"}'
```

Als het goed is krijg je een response zoals:

```json
{"token":"qidl6dxxtzhlm5ya"}
```

5. En daarna gebruik je dit token in een opvolg `curl` request:

```console
curl -X POST "http://localhost:8080/checkAppAccess?token=qidl6dxxtzhlm5ya" -H "Content-Type: application/json" -d '{"username": "edevries", "application": "triptop"}'
```

En dan krijg je als het goed is deze JSON:

```json
{"access":"allowed","role":"klant"}
```

<details>
<summary>Orginele request naar wiremockapi.cloud</summary>

```console
curl -X POST "https://triptop-identity.wiremockapi.cloud/login" -H "Content-Type: application/json" -d '{"username": "edevries", "password": "<password>"}'
```

</details>
