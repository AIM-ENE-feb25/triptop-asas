# ADR: Keuze van Design Pattern - Adapter Pattern

**Status**: Geaccepteerd  
**Datum**: 28 maart 2025

## Context
In het backend systeem van de TripTop applicatie willen we externe services zoals boekingen, betalingen, routeplanning en authenticatie integreren. Deze externe services bieden verschillende interfaces en protocollen die niet direct overeenkomen met de interne architectuur van ons systeem. Om deze externe services op een uniforme manier te kunnen aanroepen, moeten we een design pattern kiezen dat het mogelijk maakt om deze variëteiten van interfaces aan te passen aan onze interne vereisten.

## Opties Overwogen

- **State Pattern**: Dit patroon is ontworpen om de toestand van een object te beheren en zou complexer zijn dan nodig voor onze situatie, aangezien we geen toestandsbeheer nodig hebben voor de integratie van externe systemen.

- **Strategy Pattern**: Dit patroon is geschikt voor het variëren van algoritmes binnen een context, maar biedt geen oplossing voor het aanpassen van interfaces van externe systemen. Het is dus niet direct van toepassing op onze integratiebehoefte.

- **Adapter Pattern**: Dit patroon biedt een uitstekende oplossing voor het aanpassen van verschillende interfaces van externe systemen naar de interne vereisten van ons systeem. Het maakt het mogelijk om een enkele, uniforme interface voor alle externe communicatie te creëren, zonder dat we de externe systemen zelf hoeven aan te passen.

- **Facade Pattern**: Het Facade Pattern biedt een vereenvoudigde interface voor een groep subsystemen, maar maakt geen aanpassingen aan de interne interfaces. Dit zou een oplossing kunnen zijn als we alle integraties via één vereenvoudigde API willen aanbieden, maar we moeten nog steeds met verschillende interfaces werken die aangepast moeten worden.

- **Factory Method Pattern**: Dit patroon is gericht op het creëren van objecten via een centrale methode, wat handig is voor objectcreatie maar niet geschikt is voor het aanpassen van interfaces.

## Beslissing
Het **Adapter Pattern** is het beste patroon voor deze situatie. Dit patroon biedt een flexibele manier om de verschillende externe systemen die TripTop moet integreren te verbinden met de interne architectuur. Het maakt het mogelijk om een enkele, uniforme interface voor alle externe communicatie te creëren, zonder dat we de externe systemen zelf hoeven te wijzigen.

Door adapters te gebruiken, kunnen we ervoor zorgen dat de verschillende externe systemen, zoals de betaling- en boekingssystemen, compatibel zijn met onze interne services zonder dat we de externe systemen zelf hoeven aan te passen. Dit levert een schaalbare oplossing op die eenvoudig uitbreidbaar is voor nieuwe externe integraties in de toekomst.

## Voordelen

1. **Flexibiliteit in integratie**: Het Adapter Pattern maakt het mogelijk om verschillende externe systemen met verschillende interfaces te integreren zonder de externe systemen zelf te hoeven aanpassen.

2. **Uniforme interface**: Externe systemen worden aangepast naar een uniforme interne interface, wat de communicatie vereenvoudigt en de interne code consistent houdt.

3. **Schaalbaarheid**: Nieuwe externe systemen kunnen eenvoudig worden toegevoegd door simpelweg nieuwe adapters te implementeren, zonder dat de bestaande architectuur aangepast hoeft te worden.

4. **Eenvoudig testen**: De adapters kunnen afzonderlijk worden getest, wat het testen van de integratie met externe systemen eenvoudiger maakt.

5. **Beheer van complexiteit**: Het gebruik van adapters helpt om de complexiteit van externe systeemintegraties in beheersbare lagen te verdelen, wat het makkelijker maakt om wijzigingen door te voeren zonder grote impact op de rest van het systeem.

## Nadelen

1. **Extra code en complexiteit**: Het toevoegen van adapters voegt extra klassen toe aan het systeem, wat kan leiden tot meer code en mogelijk meer complexiteit in het onderhoud.

2. **Verhoogd aantal klassen**: Voor elke externe service moet een aparte adapter worden gemaakt, wat kan leiden tot een groot aantal klassen als het aantal externe integraties toeneemt.

3. **Prestaties**: Het gebruik van adapters kan een kleine prestatiedaling veroorzaken vanwege de extra laag van abstractie die wordt toegevoegd voor het aanpassen van interfaces.

4. **Moeilijkheden bij grote veranderingen**: Als externe systemen regelmatig hun interfaces wijzigen, kan het onderhouden van de adapters extra werk vereisen om de compatibiliteit te behouden.

5. **Afhankelijkheid van adapters**: Als adapters slecht zijn geïmplementeerd of niet goed worden onderhouden, kunnen ze de communicatie met de externe systemen verstoren, wat tot moeilijkheden kan leiden bij het oplossen van integratieproblemen.


## Gevolgen
- Er zullen extra klassen nodig zijn die de externe systemen adapteren naar een interne interface.
- Elke nieuwe externe service zal een aparte adapter vereisen.
- De adapters kunnen eenvoudiger worden getest, omdat ze een afzonderlijke laag vormen die de communicatie tussen ons systeem en de externe systemen afhandelt.

## Alternatieven
- **Strategy pattern** zou kunnen worden gebruikt om verschillende algoritmes voor de integratie van externe systemen te implementeren, maar het zou niet de interfaceproblemen oplossen.

## Conclusie
Het **Adapter Pattern** is de beste keuze voor het integreren van externe systemen in TripTop, omdat het de interfaceproblemen oplost en de onderhoudbaarheid van de code verbetert door het gebruik van adapters voor verschillende systemen.
