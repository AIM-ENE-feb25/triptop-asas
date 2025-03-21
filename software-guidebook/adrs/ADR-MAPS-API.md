# ADR: Besluit over API voor routebeschrijving

## Context
Voor onze applicatie hebben we een API nodig die routebeschrijvingen kan genereren. De API moet nauwkeurige route-informatie bieden, goed schaalbaar zijn en eenvoudig te integreren met onze bestaande infrastructuur. Belangrijke overwegingen zijn kosten, dekking en beschikbaarheid.

## Besluit
We hebben besloten om de rapidAPI te gebruiken voor de routebeschrijving in onze app. Dit komt omdat deze geen kosten met zich mee brengt en omdat er geen creditcard vereist is om deze te gebruiken. Ook is dit een "fake" API, wat betekent dat deze geen echte routebeschrijvingen genereert, maar wel de functionaliteit biedt om te testen of de routebeschrijvingen in de app werken. Echter krijg je van deze API alleen een JSON bestand met een routebeschrijving, en geen visuele weergave van de route.

| Criteria                   | RapidAPI | Google Maps API | Mapbox Directions API |
|----------------------------|----------|-----------------|-----------------------|
| **Nauwkeurigheid**         | --       | ++              | +                     |
| **Kosten**                 | ++       | --              | -                     |
| **Integratiegemak**        | ++       | ++              | ++                    |
| **Beschikbaarheid**        | +        | ++              | ++                    |
| **Visualisatie opties**    | --       | ++              | ++                    |
| **Documentatie**           | +        | ++              | +                     |

## Alternatieven
1. **Google Maps API** – Zeer nauwkeurig en breed ondersteund, maar relatief duur bij schaalvergroting.
3. **Mapbox Directions API** – Goede visualisatie-opties en concurrerend geprijsd, maar minder dekking dan Google Maps.

## Consequenties
- **Voordelen**: We krijgen een robuuste en goed gedocumenteerde API die eenvoudig te integreren is.
- **Nadelen**: Afhankelijk van de gekozen API kunnen er kosten en beperkingen zijn in gebruik of beschikbaarheid.
- **Risico’s**: Mogelijke latere migratie naar een andere API als onze behoeften veranderen.

## Status
- [✓] Goedgekeurd