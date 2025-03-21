## Domeinmodel Mapping naar Stripe API

| Class::attribuut | Is input voor API+Endpoint | Wordt gevuld door API+Eindpoint | Wordt geleverd door eindgebruiker | Moet worden opgeslagen in de applicatie |
| --- | --- | --- | --- | --- |
| TriptopGebruiker::email | Stripe /v1/customers (POST) |   | X | X |
| TriptopGebruiker::voornaam | Stripe /v1/customers (POST) |   | X | X |
| TriptopGebruiker::achternaam | Stripe /v1/customers (POST) |   | X | X |
| Trip::budget |   |   | X | X |
| Trip::berekenPrijs() | Stripe /v1/payment\_intents (POST) |   |   | X |
| Reservering::reserveringsnummer | Stripe /v1/payment\_intents (POST) |   |   | X |
| Reservering::status |   | Stripe /v1/payment\_intents/{id} (GET) |   | X |
| \- |   | Stripe customer\_id |   | X |
| \- |   | Stripe payment\_intent\_id |   | X |

## Ontbrekende Data-Analyse

## Wat mist er in het domeinmodel dat de API nodig heeft:

1.  **Betaalgegevens**: Het domeinmodel heeft geen klasse of attributen voor betalingsinformatie
    
    -   Oplossing: Creëer een nieuwe `Betaling` klasse die gerelateerd is aan `Reservering`
        
2.  **Valuta**: Trip.berekenPrijs() geeft alleen een bedrag, maar geen valuta
    
    -   Oplossing: Voeg valuta toe aan Trip of Reservering klasse
        
3.  **Betalingsstatus**: ReserveringsStatus is te algemeen voor het traceren van betalingsstatussen
    
    -   Oplossing: Voeg een gedetailleerdere BetalingsStatus enum toe
        

## Wat mist er in de API-response die het domeinmodel nodig heeft:

1.  **Transactiereferenties**: Stripe geeft payment\_intent\_id en charge\_id die opgeslagen moeten worden
    
    -   Oplossing: Voeg deze velden toe aan de Betaling klasse
        
2.  **Betalingsbevestiging**: Gegevens over wanneer een betaling is bevestigd
    
    -   Oplossing: Voeg timestamp en bevestigingsgegevens toe aan Reservering of Betaling
        

## Implementatie-overwegingen

1.  **Beveiligingsaspecten**: Stripe API keys moeten veilig worden opgeslagen
    
    -   Gebruik environment variables voor API keys
        
    -   Implementeer Bearer auth voor authenticatie
        
2.  **Frontend vs Backend**: Bepaal waar de Stripe-aanroepen plaatsvinden
    
    -   Frontend: Gebruik Stripe.js voor veilige betalingsformulieren
        
    -   Backend: Gebruik de Stripe SDK voor server-side operaties
        
3.  **Foutafhandeling**: Implementeer robuuste error handling
    
    -   Maak gebruik van try-catch blokken
        
    -   Implementeer retry-mechanismen voor tijdelijke fouten
        

Door deze aanpak kan de Triptop-applicatie veilig betalingen verwerken via Stripe terwijl we het domeinmodel uitbreiden met de benodigde betalingsinformatie.