
### **ADR-005: Database Keuze voor Triptop-systeem**

#### **Status**  
Accepted

#### **Context**  
Triptop is een online platform dat reizigers in staat stelt om zelfstandig reizen te plannen, boeken, annuleren en beheren. Aangezien kritieke gegevens zoals boekingen, gebruikersprofielen, reis- en betalingsinformatie worden verwerkt, is het essentieel dat de gekozen database:
- Sterke transactionele integriteit (ACID-naleving) biedt.
- Hoge dataconsistentie garandeert.
- Beschikt over een robuust ecosysteem en goede ondersteuning.
- Schaalbaar is voor groeiende gebruikersaantallen.
- Kosteneffectief is en aansluit bij de bestaande expertise van het team.

Gezien de uitgebreide ervaring van ons team met MSSQL en het feit dat we MSSQL kosteloos kunnen gebruiken, is dit een belangrijke factor in onze beslissing.

#### **Decision Forces / Criteria**

| **Criteria**                      | **MSSQL** | **PostgreSQL** | **MongoDB** |
|-----------------------------------|:---------:|:--------------:|:-----------:|
| **ACID-naleving**                 | ++        | ++             | --          |
| **Dataconsistentie**              | ++        | ++             | --          |
| **Schaalbaarheid**                | +         | +              | ++          |
| **Community & Ecosysteem**        | +         | ++             | +           |
| **Gebruiksgemak**                 | ++        | +              | +           |
| **Licenties & Kosten**            | ++        | ++             | ++          |
| **Team Ervaring**                 | ++        | +              | --          |

*Let op: In onze context heeft MSSQL als licentie-oplossing geen bijkomende kosten, wat resulteert in een score van "++" bij Licenties & Kosten. Wij kunnen MSSQL gratis gebruiken, vanwege school*

#### **Alternatieven**  
- MSSQL  
- PostgreSQL  
- MongoDB

#### **Decision**  
Wij kiezen voor **MSSQL** als onze primaire database-oplossing voor Triptop. De keuze is gebaseerd op de volgende punten:
- **Team Ervaring:** Ons team heeft uitgebreide ervaring met MSSQL, wat zorgt voor een kortere implementatietijd en snellere probleemoplossing.
- **Gebruiksgemak:** MSSQL biedt een geïntegreerde en ontwikkelaarsvriendelijke omgeving met uitgebreide tooling en ondersteuning.
- **Transactionele Integriteit en Dataconsistentie:** MSSQL voldoet volledig aan de ACID-vereisten, wat essentieel is voor het verwerken van boekingen en betalingen.
- **Licenties & Kosten:** In onze situatie kunnen we MSSQL gratis gebruiken, waardoor licentiekosten geen probleem vormen.

Hoewel PostgreSQL en MongoDB hun eigen voordelen hebben, weegt onze ervaring en de gratis beschikbaarheid van MSSQL zwaarder in de besluitvorming.

#### **Consequences**  
- **Transactionele Integriteit:** Onze boekings- en betalingsprocessen profiteren van de robuuste ACID-naleving van MSSQL.
- **Dataconsistentie:** Gegevens blijven consistent en betrouwbaar, wat essentieel is voor het verwerken van kritieke transacties.
- **Gebruiksgemak en Ondersteuning:** De uitgebreide ervaring van ons team met MSSQL zorgt voor een kortere implementatietijd en snellere probleemoplossing.
- **Schaalbaarheid:** MSSQL voldoet ruim voldoende aan de huidige en nabije toekomstige eisen, ondanks dat sommige NoSQL-oplossingen beter schalen op horizontaal vlak.
- **Licentiekosten:** Aangezien we MSSQL gratis kunnen gebruiken, zijn er geen bijkomende licentiekosten, wat de totale investering verlaagt.
- **Ecosysteem:** MSSQL biedt een stabiel ecosysteem en uitgebreide ondersteuning vanuit Microsoft, wat bijdraagt aan de betrouwbaarheid en toekomstbestendigheid van onze applicatie.

---
