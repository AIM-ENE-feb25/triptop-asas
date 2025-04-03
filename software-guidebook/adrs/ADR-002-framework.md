### 8.1. ADR-001 Framework keuze voor Triptop systeem


#### Context

Om te komen tot een goed werkend prototype is het noodzakelijk om de keuze te maken voor de juiste
framework om dit te realiseren.


#### Overwogen Opties

| **Criteria**                          | **Spring Boot** | **Quarkus** | **Micronaut** | **Jakarta EE** | **Vert.x** | **Dropwizard** | **Helidon** |
|---------------------------------------|-----------------|-------------|---------------|----------------|------------|----------------|-------------|
| **Makkelijk in gebruik <sup>1</sup>** | ++              | +           | +             |                | -          | +              |             |
| **Snelheid opstarttijd <sup>2</sup>** |        | ++          | +             | -              | ++         |       | +           |
| **Geheugengebruik <sup>3</sup>**      |        | -           | -             | +              | -          |       | _           |

#### Bronnen

<sup>1</sup> Johns, R. (n.d.). Want to level up in Java? Use these Java frameworks! Hackr.io. https://hackr.io/blog/java-frameworks

<sup>2</sup> Dansiviter. (2021, June 27). Opinionated take on Java Microservices Frameworks. DEV Community. https://dev.to/dansiviter/opinionated-take-on-java-microservices-frameworks-4ebh

<sup>3</sup> 4 Microservice frameworks in comparison, streaming example included. (n.d.). https://blogs.itemis.com/en/4-microservice-frameworks-in-comparsion-example-included

#### Besluit
We hebben gekozen om gebruik te maken van het framework Spring Boot, omdat dit het makkelijkste in gebruik is en een gemiddelde snelheid heeft.  Dit is belangrijk voor het prototype, omdat we snel een werkend prototype willen hebben en het makkelijk in gebruik moet zijn. 
Daarnaast hebben wij al ervaring met dit framework, daarom is dit een logische keuze.

#### Status
Accepted

#### Consequenties
Door gebruik te maken van Spring Boot, zullen wij minder snelheidsproblemen hebben en is het makkelijker om het prototype te realiseren. 
Daarnaast zullen wij minder tijd kwijt zijn aan het leren van een nieuw framework, omdat wij al ervaring hebben met Spring Boot.





