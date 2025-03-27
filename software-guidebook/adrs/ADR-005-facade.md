### 8.5. ADR-005  Keuze voor waar implementatie interface


#### Context

Het is belangrijk om een wel overwogen keuze te maken over welk design pattern wordt gebruikt om de data vanuit een extern systeem (Booking.com) en data vanuit een intern systeem (TripTopService) te combineren en op te slaan.


#### Overwogen Opties

| **Criteria**                 | **State Patern** | **Strategy pattern** | **Adapter Pattern** | **Facade Pattern** | **Factory Method Pattern** |
|------------------------------|------------------|----------------------|---------------------|--------------------| --------------------------- |
| **Mate van afhankelijkheid** | -                | -                    | +                   |                    |
| ****                         |                  | ++                   | +                   |                    |
| ****                         |                  | -                    | -                   |                    |



#### Welk probleem lost het op
Het facade pattern zorgt ervoor dat het systeem data vanuit een extern systeem(Booking.com) en data vanuit een intern systeem (TripTopService) kan combineren en dit kan opslaan.

#### Design principles
Deze design pattern is gebaseerd op de volgende design principles:
- Single Responsibility Principle
- Dependency Inversion Principle

#### Status
Accepted

#### Consequenties
##### Voordelen

##### Nadelen
Het is mogelijk dat de facade class te groot wordt en dat het moeilijk wordt om de code te onderhouden. Daarnaast is het lastig om nieuwe api's of interne systemen toe te voegen aan de facade class.





