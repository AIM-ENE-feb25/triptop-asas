### 8.5. ADR-005  Keuze voor waar implementatie interface


#### Context

Het is belangrijk om een wel ovewegen keuze te maken in welk component de interface voor de facade komt.


#### Overwogen Opties

| **Criteria**                 | **BookingComAdapter class** | **TripTopService class** | **FacadeService class** | 
|------------------------------|-----------------------------|--------------------------|-------------------------|
| **Mate van afhankelijkheid** | -                           | -                        | +                       |
| ****                         |                             | ++                       | +                       | 
| ****          |                             | -                        | -                       | 



#### Besluit
Ik heb besloten om de interface voor de facade in de facade class te zetten. 
Dit zorgt ervoor dat de BookingComAdapter en TripTopService klassen niet afhankelijk van ekaar zijn.


#### Status
Accepted

#### Consequenties
Door de interface in de facade class te zetten, zullen de BookingComAdapter en TripTopService klassen niet afhankelijk van elkaar zijn.




