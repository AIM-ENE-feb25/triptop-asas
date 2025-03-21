## 8.2. ADR-002 API Gateway keuze voor Triptop systeem

## Context

Voor het TripTop-systeem hebben we overwogen om een API gateway te implementeren als tussenlaag tussen onze backend en de externe services waarmee we communiceren. Een API gateway kan voordelen bieden zoals gecentraliseerde authenticatie, rate limiting, en monitoring. Echter, de huidige schaal en complexiteit van ons systeem vereisen een zorgvuldige afweging van de toegevoegde waarde versus de kosten en complexiteit.

## Overwogen Opties

| **Criteria** | **Geen API Gateway** | **API Gateway Implementatie** | **Backend-for-Frontend (BFF)** |
| --- | --- | --- | --- |
| **Architectuur complexiteit** | ++ | \- | \-- |
| **Latentie** | ++ | \- | + |
| **Kosten** | ++ | \-- | \- |
| **Beheer & onderhoud** | + | \- | \-- |
| **Schaalbaarheid** | \- | ++ | + |
| **Ontwikkelingssnelheid** | ++ | \- | + |

## Besluit

We hebben besloten om geen API gateway te implementeren in ons systeem. In plaats daarvan blijft onze backend direct communiceren met externe services via REST API's. Dit is belangrijk voor het TripTop-systeem omdat een eenvoudigere architectuur met minder complexiteit beter past bij onze huidige schaal.

## Status

Accepted

## Consequenties

Door geen API gateway te implementeren, bereiken we:

-   Lagere kosten en eenvoudiger beheer van onze infrastructuur
    
-   Minder infrastructuurcomponenten om te onderhouden
    
-   Snellere responstijden door directe communicatie
    
-   Snellere ontwikkeling van nieuwe functionaliteiten
    

We accepteren hierbij de volgende nadelen:

-   Geen gecentraliseerde plek voor cross-cutting concerns zoals rate limiting of monitoring
    
-   Onze backend moet alle integratiefuncties zelf afhandelen
    
-   Schaalbaarheid kan in de toekomst een uitdaging worden bij toenemende complexiteit
    

Deze beslissing zal worden herzien indien de schaal of eisen van ons systeem significant veranderen, bijvoorbeeld bij een toename in het aantal gebruikers of externe services waarmee we integreren.