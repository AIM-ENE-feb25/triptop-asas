# ADR-001: Gebruik van het Adapter Pattern voor Externe Authenticatie Integratie  
## **Status:** Geaccepteerd

## **Context**  
Onze applicatie integreert externe authenticatieproviders, zoals de WireMock API. Er is een noodzaak om te voorkomen dat wijzigingen in de externe API – bijvoorbeeld wijzigingen in endpoints of responstructuren – leiden tot ingrijpende aanpassingen in de applicatie, met name in de front-end. We willen dat de back-end flexibel is en externe veranderingen opvangt via een stabiel intern contract. Hierdoor kan de front-end ongewijzigd blijven, ongeacht wijzigingen in de externe services.

## **Decision Forces**

| Criteria                                        | Belang  | State Pattern | Strategy Pattern | Adapter Pattern | Facade Pattern | Factory Method Pattern |
|-------------------------------------------------|---------|---------------|------------------|-----------------|----------------|------------------------|
| Losse koppeling tussen intern en extern         | Hoog    | --            | -                | ++             | +              | -                      |
| Flexibiliteit bij API-wijzigingen               | Hoog    | --            | -                | ++             | +              | --                     |
| Eenvoud van uitbreiding                         | Matig   | -             | +                | ++             | +              | +                      |
| Gericht op interfacevertaling                   | Hoog    | --            | --               | ++             | -              | --                     |
| Complexiteit en onderhoudbaarheid               | Matig   | -             | +                | ++             | +              | -                      |

## **Alternatieven**
- **State Pattern:**  
  -- Niet geschikt, omdat dit patroon bedoeld is om gedrag op basis van interne status te beheren, niet om externe API-contracten te vertalen.

- **Strategy Pattern:**
    - Kan verschillende algoritmen encapsuleren, maar biedt onvoldoende isolatie voor externe API-aanpassingen.

- **Adapter Pattern:**  
  ++ Dit patroon creëert een tussenlaag die het interne contract (Login Port) vertaalt naar externe API-aanroepen. Hierdoor blijft de interne logica stabiel en is de applicatie makkelijk uitbreidbaar.

- **Facade Pattern:**
    + Biedt een vereenvoudigde interface voor complexe subsystemen, maar is minder gericht op het vertalen van externe API-verschillen.

- **Factory Method Pattern:**
    - Richt zich op objectcreatie en verbergt de creatie van objecten, maar lost niet direct het probleem op van een stabiel contract met externe API’s.

## **Beslissing**  
Wij kiezen voor het **Adapter Pattern**. Dit patroon stelt ons in staat om een stabiel intern contract te definiëren via de Login Port, waarna de concrete adapters (bijvoorbeeld WireMock Adapter v1 en v2) de vertaling verzorgen naar de externe API-aanroepen. Hierdoor blijft de businesslogica van de applicatie onveranderd, zelfs als de externe API wijzigt. De keuze voor het Adapter Pattern ondersteunt het Open/Closed Principe: we kunnen nieuwe adapter-implementaties toevoegen (bijvoorbeeld voor een nieuwe API-versie) zonder bestaande code te wijzigen. Bovendien volgt deze keuze het Single Responsibility Principe, omdat elke component precies één taak heeft.

## **Consequenties**
- **Voordelen:**  
  ++ Losse koppeling tussen de interne logica en de externe API’s.  
  ++ Flexibiliteit om meerdere versies van een externe API te ondersteunen (bijvoorbeeld WireMock v1 en v2).  
  ++ Eenvoudige uitbreiding en onderhoud; nieuwe adapters kunnen worden toegevoegd zonder ingrijpende aanpassingen in de Login Service of de front-end.  
  ++ Stabiliteit voor de front-end, omdat deze enkel communiceert via het vastgelegde contract (Login Port).

- **Risico's en Nadelen:**
    - Extra complexiteit in de back-end door de introductie van meerdere adapter-implementaties.
    - Mogelijke duplicatie van functionaliteit indien adapters overlappende taken hebben, wat nauwgezet beheer vereist.

