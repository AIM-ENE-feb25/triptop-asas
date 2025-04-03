## ontwerpvraag
Ik heb voor dit project gekozen voor de ontwerpvraag: Hoe zorg je ervoor dat je makkelijk de ene externe service kan vervangen door een andere die ongeveer hetzelfde doet?
Ik vond deze vraag interessant omdat ik in mijn project met verschillende externe services moet werken en het belangrijk is dat ik deze makkelijk kan vervangen zonder dat dit invloed heeft op de rest van de applicatie.

## Bijpassend design pattern
Het design pattern waar ik voor gekozen heb is het Adapter pattern. Dit pattern maakt het mogelijk om de communicatie met externe services op een uniforme manier te organiseren. Door gebruik te maken van adapters kan ik verschillende externe services integreren zonder dat dit invloed heeft op de rest van de applicatie. Dit zorgt ervoor dat ik makkelijk kan switchen tussen verschillende services zonder dat ik de rest van de applicatie hoef aan te passen.

Ik heb voor dit project meerdere design patterns overwogen, maar uiteindelijk gekozen voor het Adapter pattern omdat dit het beste aansluit bij mijn ontwerpvraag. Het Strategy Pattern bijvoorbeeld zou ook een optie zijn, maar dit zou meer complexiteit met zich meebrengen dan nodig is voor dit project. Het Adapter pattern is eenvoudig te implementeren en zorgt ervoor dat ik makkelijk kan switchen tussen verschillende externe services zonder dat dit invloed heeft op de rest van de applicatie.

In dit geval maak ik gebruik van de Paypal API en de Stripe API. Beide APIs hebben verschillende endpoints en vereisen verschillende parameters, maar door gebruik te maken van adapters kan ik deze verschillen opvangen en de communicatie met de externe services op een uniforme manier organiseren.

## Design Principle
Ik heb ervoor gekozen om het Dependency Inversion Principle (DIP) toe te passen in mijn ontwerp. Dit principe stelt dat hoog-niveau modules niet afhankelijk moeten zijn van laag-niveau modules, maar beide afhankelijk moeten zijn van abstracties. Dit betekent dat ik de adapters en de service moet scheiden, zodat de service niet afhankelijk is van een specifieke adapter, maar alleen van de abstractie (de PaymentPort). Hierdoor kan ik makkelijk switchen tussen verschillende adapters zonder dat dit invloed heeft op de rest van de applicatie.


## Component diagram
![component diagram](Component%20diagram.png)

Hierboven zie je mijn component diagram. Ik heb in dit geval alleen het gedeelte van de betalingen gemaakt. 
Wat goed is im in het achterhoofd te houden is dat er wel sprake is van een payment Port, maar ik deze niet heb meegenomen omdat deze in de Service zit. 
Verder toon ik in dit diagram alleen de stripe adapter. De bedoeling is dat ik de stripe adapter en de paypal adapter gemakkelijk moet kunnen omwisselen.

## Dynamic component diagram
![Dynamic component diagram](Dynamic Component%20diagram.png)

Hierboven zie je mijn dynamische component diagram. Dit diagram laat zien hoe de verschillende componenten met elkaar communiceren en hoe de adapters de communicatie met de externe services afhandelen.


## code diagram
![code diagram](Code%20diagram.png)

Hierboven zie je mijn code diagram. Hier ga ik specifiek in op de technische zaken. Ik heb voor dit prototype goed gekeken naar wat de gemene deler was voor het afhandelen van een betaling voor de stripe en paypal API. In beide gevallen moest er eerst een "order" gemaakt worden die vervolgens geaccepteerd kan worden. Ik heb de functies voor het maken van de order en het accepteren van de order in een "PaymentPort" gezet. Dit zorgt ervoor dat ik deze functies makkelijk kan implementeren in de adapters voor de verschillende externe services. 
Hierdoor kan ik door middel van het aangeven in de properties gemakkelijk omschakelen van Paypal naar Stripe.
De betalingen worden uiteraard ook netjes opgeslagen in de database.

## sequence diagram
![sequence diagram](Sequence%20diagram.png)

Hierboven zie je mijn sequence diagram. Dit diagram laat zien hoe de verschillende componenten met elkaar communiceren en hoe de adapters de communicatie met de externe services afhandelen.