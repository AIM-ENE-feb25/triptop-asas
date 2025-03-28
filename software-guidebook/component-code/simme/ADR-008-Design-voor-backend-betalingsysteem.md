# ADR: Besluit over Betalingssysteem Integratie Architectuur

## Context
Voor onze TripTop applicatie moeten we verschillende betalingssystemen integreren om betalingen voor trips en reserveringen te verwerken. We hebben een flexibele architectuur nodig die verschillende betalingsproviders ondersteunt (beginnend met Stripe en PayPal), schaalbaar is voor toekomstige uitbreidingen, en gemakkelijk te testen en onderhouden is.

Belangrijke overwegingen zijn:
- Ondersteuning van meerdere betalingsproviders
- Minimaliseren van codeduplicatie
- Waarborgen van testbaarheid
- Eenvoudig toevoegen van nieuwe betalingsmethoden
- Schone scheiding tussen betalingslogica en bedrijfslogica

## Besluit
We hebben besloten om de betalingsintegratie te implementeren met een combinatie van het Factory Pattern en Adapter Pattern. Deze architectuur stelt ons in staat om:

1. Een gemeenschappelijke interface (`BetalingAdapter`) te definiëren die door alle betalingsproviders wordt geïmplementeerd
2. Een factory (`BetalingFactory`) te gebruiken om dynamisch de juiste adapter te creëren op basis van de betalingsmethode
3. Betalingsspecifieke implementatiedetails te isoleren in hun respectievelijke adapterklassen
4. Eenvoudig nieuwe betalingsproviders toe te voegen in de toekomst

| Criteria                   | Factory + Adapter Pattern | Hardcoded Implementatie | Service Container Binding | Dynamische Selectie (if-else) | Externe Betalings-gateway |
|----------------------------|---------------------------|--------------------------|---------------------------|-------------------------------|--------------------------|
| **Flexibiliteit**          | ++                        | --                       | +                         | -                             | +                        |
| **Onderhoudbaarheid**      | ++                        | --                       | +                         | -                             | ++                       |
| **Ontwikkelingsefficiëntie** | +                       | ++                       | +                         | ++                            | -                        |
| **Testbaarheid**           | ++                        | +                        | ++                        | +                             | -                        |
| **Complexiteit**           | -                         | ++                       | -                         | +                             | ++                       |
| **Toekomstige uitbreiding** | ++                       | --                       | +                         | --                            | +                        |

## Alternatieven
1. **Hardcoded Implementatie** – Direct implementeren van betalingslogica zonder abstracties. Eenvoudig voor één betalingsprovider maar moeilijk te onderhouden en uit te breiden.

2. **Service Container Binding** – Gebruik van dependency injection om interfaces te binden tijdens compilatie. Goed voor configuratie-gebaseerde selectie maar minder flexibel tijdens runtime.

3. **Dynamische Selectie zonder Factory** – Gebruik van if-else of switch statements om betalingsproviders te selecteren. Eenvoudige implementatie maar schendt het open-closed principe en leidt tot uitdijende code.

4. **Externe Betalings-gateway** – Gebruik van een externe service die al meerdere betalingsproviders integreert. Vermindert ontwikkelingsinspanning maar verhoogt afhankelijkheid en potentieel de kosten.

## Consequenties
- **Voordelen**:  
  - Schone scheiding van verantwoordelijkheden
  - Eenvoudig toevoegen van nieuwe betalingsproviders
  - Testbare architectuur (kan adapters mocken)
  - Betalingsspecifieke logica geïsoleerd in adapters
  - Volgt SOLID-principes

- **Nadelen**:  
  - Verhoogde initiële complexiteit met meer klassen
  - Extra abstractielaag kan prestaties licht beïnvloeden
  - Vereist goede documentatie voor ontwikkelaars om het patroon te begrijpen

- **Risico's**:  
  - Over-engineering als we ooit maar één betalingsprovider gebruiken
  - Patroonimplementatie moet consistent zijn om voordelen te behouden
  - Mogelijk meer complexiteit bij integratie dan verwacht

## Status
- [✓] Goedgekeurd
