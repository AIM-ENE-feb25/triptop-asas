@startuml
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Context.puml
title Systeem Context Diagram voor TripTop Applicatie
SHOW_PERSON_OUTLINE()
Person(reiziger, "Reiziger", "Stelt de reis zelf samen, boekt/annuleert/ past aan en betaalt")
Person(ondersteuner, "Ondersteuner", "Tweede­lijns support; helpt de reiziger bij vragen.")

System(triptop, "TripTop", "Online platform voor het plannen, boeken, annuleren en beheren van reizen")

System_Ext(overnachtingen, "Overnachtingen (Booking, Airbnb, Eigen beheer)", "Biedt overnachtings­mogelijkheden")
System_Ext(autohuur, "Autohuur (Sixt, Hertz)", "Biedt huurauto’s voor reizigers")
System_Ext(activiteiten, "Activiteiten (GetYourGuide, Viator, Tripadvisor)", "Biedt excursies/activiteiten")
System_Ext(vervoer1, "Vervoer (NS, Deutsche Bahn, KLM)", "Vervoer tussen overnachtingen (trein, vliegtuig)")
System_Ext(vervoer2, "Routeplanner ( Google Maps API)", "Geeft routes voor de punten waar de reiziger langs wilt.")
System_Ext(eten, "Eten & Drinken (Takeaway, Eet.nu)", "Maaltijd- en restaurant­opties")

System_Ext(identity, "Identity Provider (Google, Microsoft, Airbnb)", "Biedt centrale login zonder extra account aan te maken")
System_Ext(payment, "Betalings­systemen (iDEAL, PayPal, Creditcard)", "Verzorgt betalingen voor TripTop")

Rel(reiziger, triptop, "Gebruikt TripTop voor het plannen, boeken, aanpassen en annuleren van de reis")
Rel(ondersteuner, triptop, "Ondersteunt de reiziger via TripTop (advies)")
Rel(triptop, overnachtingen, "Haalt overnachtings­mogelijkheden op en boekt indien nodig")
Rel(triptop, autohuur, "Haalt autohuur­mogelijkheden op en boekt deze")
Rel(triptop, activiteiten, "Haalt activiteiten/excursies op en boekt deze")
Rel(triptop, vervoer1, "Haalt reisopties (trein/vliegtuig) op en boekt deze")
Rel(triptop, vervoer2, "Haalt alternatieve vervoersopties op (bijv. taxi of routeplan)")
Rel(triptop, eten, "Haalt eet- en drink­mogelijkheden op (restaurants/bezorging)")

Rel(triptop, identity, "Gebruikt SSO om reizigers te authenticeren")
Rel(triptop, payment, "Maakt gebruik van externe betalings­systemen voor afrekenen")

@enduml
