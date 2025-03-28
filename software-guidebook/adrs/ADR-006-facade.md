### 8.5. ADR-006  Keuze voor waar implementatie interface

#### Status
Geaccepteerd

#### Context

Het is belangrijk om een wel overwogen keuze te maken over welk design pattern wordt gebruikt om de data vanuit een extern systeem (Booking.com) en data vanuit een intern systeem (TripTopService) te combineren en op te slaan.


#### Overwogen Opties

| **Criteria**                  | **State Pattern** | **Strategy Pattern** | **Adapter Pattern** | **Facade Pattern** | **Factory Method Pattern** |
|--------------------------------|-------------------|----------------------|---------------------|--------------------|----------------------------|
| **Mate van afhankelijkheid**   | -                 | -                    | +                   | +                  | -                          |
| **Loskoppeling**               | +                 | ++                   | +                   | ++                 | ++                         | 
| **Uitbreidbaarheid**           | ++                | +                    | -                   | -                  | ++                         |
| **Integratie met meerdere klassen** | +          | +                    | ++                  | ++                 | +                          |

#### Alternatieven

- Adapter Pattern: Dit patroon creëert een tussenlaag die het interne contract (Login Port) vertaalt naar externe API-aanroepen. Hierdoor blijft de interne logica stabiel en is de applicatie makkelijk uitbreidbaar.

##### Design principles
Deze design pattern is gebaseerd op de volgende design principles:
- Single Responsibility Principle
- Dependency Inversion Principle

#### Consequenties

#### Voordelen
Het facade pattern zorgt ervoor dat het systeem data vanuit een extern systeem(Booking.com) en data vanuit een intern systeem (TripTopService) kan combineren en dit kan opslaan.


##### Nadelen
Het is mogelijk dat de facade class te groot wordt en dat het moeilijk wordt om de code te onderhouden. Daarnaast is het lastig om nieuwe api's of interne systemen toe te voegen aan de facade class.





