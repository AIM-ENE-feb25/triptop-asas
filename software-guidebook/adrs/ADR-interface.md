### **ADR-XXX: Keuze inplementatie BoekingInterface**

#### **Status**
Geaccepteerd

#### **Context**
Het is belangrijk om een goede keuze te maken op welke plek een interface geimplementeerd moet worden.

#### **Decision Forces**
| **Criteria**               | **BookingComAdapter** | **InternBoekingSysteem** | **BoekingComAdpater en InternBoekingSysteem** | **BoekingOverzichtService** | 
|----------------------------|:---------------------:|:------------------------:|:---------------------------------------------:|-----------------------------|
| **Afhankelijkheid**        |           -           |            -             |                       +                       | +                           |
| **Code duplicatie**        |                       |                          |                       -                       | +                           | 
| **Makkelijk uitbreidbaar** |           -           |            -             |                       -                       | -                           |

#### **Alternatieven**
- implementatie BookingComAdapter: zorgt ervoor dat InternBoekingSysteem afhankelijk wordt BookingComAdapter
- implementatie InternBoekingSysteem: zorgt ervoor dat BookingComAdapter afhankelijk wordt van InternBoekingSysteem
- implementatie BookingComAdapter en InternBoekingSysteem: zorgt ervoor dat de afhankelijkheid er niet is, maar zorgt wel voor code duplicatie.
- implementatie BoekingOverzichtService: zorgt ervoor dat je code duplicatie voorkomt, er geen afhankelijkheden zijn en dat de code makkelijk uitbreidbaar is.

#### **Beslissing**
Er is besloten om de klasse BoekingOverzichtService de interfacde BoekingInterface te laten implementeren.


#### **Consequenties**

##### Voordelen
- De BookingComAdapter en InterneBoekingSysteem niet direct van elkaar afhankelijk worden
- Voorkomt code duplicatie, waardoor onderhouden van het systeem eenvoudiger wordt.
- Betere scheiding van afhankelijkheden

##### Nadelen
- Elke keer als er een nieuwe klasse wordt toegevoegd, moet de interface aangepast worden.
- Extra abastractie laag kan het systeem complexer maken.