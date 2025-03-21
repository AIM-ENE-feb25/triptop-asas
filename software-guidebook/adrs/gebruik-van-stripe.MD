
# **ADR-001: Gebruik van Stripe als betalingsprovider**

**Status:**  
Geaccepteerd

## **Context**  
Onze TripTop-applicatie moet een betalingssysteem hebben waarmee gebruikers hun reis gemakkelijk kunnen betalen. Andere externe systemen (zoals voor hotels of autohuur) hebben soms hun eigen betaalmethodes, maar dat kan leiden tot een rommelige en onduidelijke gebruikerservaring. Daarom willen we één enkel systeem gebruiken dat voor iedereen werkt.

## **Decision Forces**

We hebben drie opties overwogen. Hier is een eenvoudige tabel met plussen en minnen om te laten zien wat goed (+) en minder goed (-) is aan elke optie:

| **Optie**                   | **Uniformiteit** | **Veiligheid & Regels** | **Schaalbaarheid** | **Technische Moeilijkheid** | **Afhankelijkheid** | **Kosten** |
|-----------------------------|------------------|-------------------------|--------------------|-----------------------------|---------------------|------------|
| **Stripe**                  | ++               | ++                      | ++                 | +                           | -                   | +          |
| **Externe API-betalingen**  | -                | -                       | -                  | ++                          | ++                  | ++         |
| **Eigen Betaalinfrastructuur** | -             | -                       | -                  | -                           | ++                  | -          |

- **Uniformiteit:** Stripe geeft één manier van betalen voor alle boekingen (++), terwijl externe systemen verschillende methoden hebben (-).
- **Veiligheid & Regels:** Stripe voldoet aan belangrijke regels (zoals PCI-DSS en GDPR) (++), wat bij andere opties vaak niet zo duidelijk is (-).
- **Schaalbaarheid:** Stripe groeit mee als er meer gebruikers komen (++), externe systemen zijn vaak beperkt (-).
- **Technische Moeilijkheid:** Het opzetten van Stripe is relatief makkelijk (+), terwijl eigen oplossingen vaak moeilijker zijn (-).
- **Afhankelijkheid:** Als we externe betaalmethoden gebruiken, zijn we erg afhankelijk van meerdere partijen (++), terwijl Stripe minder afhankelijk maakt (-).
- **Kosten:** Stripe rekent wel kosten per transactie (+), maar dit is acceptabel vergeleken met de nadelen van andere opties (++ of -).

## **Beslissing**

Wij kiezen voor **Stripe** als onze betalingsprovider.  
- **Waarom?**  
  - Stripe zorgt voor één uniforme betalingsmethode voor alle boekingen.  
  - Het voldoet aan alle belangrijke veiligheidsregels en kan eenvoudig meeschalen.  
  - Het maakt het technisch eenvoudiger en vermindert de afhankelijkheid van meerdere externe partijen.
  
## **Consequenties**

- Alles wordt via Stripe verwerkt, wat de gebruikerservaring eenvoudiger en consistenter maakt.
-  Stripe voldoet aan alle belangrijke veiligheidsnormen.
-  Naarmate het aantal gebruikers groeit, kan Stripe meeschalen zonder extra moeite.
- Stripe rekent kosten per transactie, wat de marges kan beïnvloeden.
- Als Stripe problemen heeft, heeft dit invloed op onze betalingsverwerking.
- Extra werk is nodig om Stripe goed te integreren en te beheren.

