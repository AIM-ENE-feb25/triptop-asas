# Contribute

## Wiremock stub data

De stub data staat in `users.csv` en er is ook een Python script die deze omzet naar juist stub mappings in de `mappings/` folder.

```console
python3 generate-wiremock-users.py
```

## Runnen met Docker

Naast lokale container kun je ook prebuilt container gebruiken, deze heet `soex-identity-wiremock` op Docker Hub: [https://hub.docker.com/repository/docker/bartvanderwal/soex-identity-wiremock/general](https://hub.docker.com/repository/docker/bartvanderwal/soex-identity-wiremock/general)

## Docker container updaten

Als je rechten hebt kun je ook evt. changes of fixes deployen als Docker Image. Onderstaande commando's zijn gebruikt (voor `latest`=`1.0.0` tag).

Omdat author een macos met M2 heeft is multi image buil gemaakt met zowel arm64 als  amd64

Eenmalig opzetten builder

```console
docker buildx create --use --name mybuilder
docker run --privileged --rm tonistiigi/binfmt --install all
```

```console
docker buildx build --platform linux/amd64,linux/arm64 -t bartvanderwal/soex-identity-wiremock:latest --push .
docker tag soex-identity-wiremock bartvanderwal/soex-identity-wiremock:1.0.0
docker push bartvanderwal/soex-identity-wiremock:1.0.0
```
