# Architectuur Beslissing Record: Geen API Gateway

## Context
Voor het TripTop-systeem hebben we overwogen om een API gateway te implementeren als tussenlaag tussen onze backend en de externe services waarmee we communiceren. Een API gateway kan voordelen bieden zoals gecentraliseerde authenticatie, rate limiting, en monitoring. Echter, de huidige schaal en complexiteit van ons systeem vereisen een zorgvuldige afweging van de toegevoegde waarde versus de kosten en complexiteit.

## Besluit
We hebben besloten **geen API gateway te implementeren** in ons systeem. In plaats daarvan blijft onze backend direct communiceren met externe services via REST API's.

## Argumenten
1. **Eenvoudigere architectuur**: Het toevoegen van een API gateway zou een extra laag complexiteit introduceren die niet noodzakelijk is voor onze huidige schaal.
2. **Lagere latentie**: Directe communicatie tussen de backend en externe services vermindert de overhead die een API gateway zou introduceren.
3. **Kostenbesparing**: Het vermijden van een API gateway elimineert extra infrastructuurkosten en onderhoud.
4. **Backend-capaciteiten**: Onze Spring Boot backend is al voorzien van functies zoals foutafhandeling, authenticatie, en logging, waardoor een gateway minder noodzakelijk is.
5. **Snellere ontwikkeling**: Zonder de noodzaak om een API gateway te configureren en beheren, kunnen we sneller nieuwe functionaliteiten ontwikkelen en uitrollen.

## Alternatieven
1. **API Gateway Implementatie (bijv. Kong, AWS API Gateway)**  
   - **Voordelen**: Gecentraliseerde beveiliging, betere monitoring, caching-mogelijkheden, eenvoudiger versiebeheer.  
   - **Nadelen**: Hogere kosten, extra complexiteit, potentieel single point of failure.

2. **Backend-for-Frontend (BFF) Patroon**  
   - **Voordelen**: Specifieke backends voor verschillende clients kunnen zorgen voor betere prestaties en flexibiliteit.  
   - **Nadelen**: Meer onderhoud door meerdere backends.

## Consequenties
### Voordelen:
- Lagere kosten en eenvoudiger beheer.
- Minder infrastructuurcomponenten om te onderhouden.
- Snellere responstijden door directe communicatie.

### Nadelen:
- Geen gecentraliseerde plek voor cross-cutting concerns zoals rate limiting of monitoring.
- Backend moet alle integratiefuncties zelf afhandelen.
- Schaalbaarheid kan in de toekomst een uitdaging worden bij toenemende complexiteit.

### Risico’s:
- Bij toekomstige groei of veranderende eisen kan er alsnog behoefte ontstaan aan een API gateway, wat migratiekosten met zich mee kan brengen.

## Status
- [✓] Goedgekeurd

Deze beslissing zal worden herzien indien de schaal of eisen van ons systeem significant veranderen, bijvoorbeeld bij een toename in het aantal gebruikers of externe services waarmee we integreren.
