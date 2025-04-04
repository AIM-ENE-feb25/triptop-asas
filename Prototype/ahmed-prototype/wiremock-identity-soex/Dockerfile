# Gebruik het officiële WireMock-image als basis
FROM wiremock/wiremock:3.12.1

# Kopieer de lokale mappings en bestanden naar de juiste directories in de container
COPY mappings /home/wiremock/mappings
COPY __files /home/wiremock/__files

# WireMock service luistert op 8080, stel poort mapping in elders als je via andere poort wil benaderen; zoals 80
EXPOSE 8080
