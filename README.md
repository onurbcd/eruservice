# Eru Service

[![Java Maven Sonar CI](https://github.com/onurbcd/eruservice/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/onurbcd/eruservice/actions/workflows/build.yml)

## Postgres in Docker

```
docker run -p5432:5432 --name postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=admin -e POSTGRES_DB=eru-service-dev -d postgres:latest
```

## Create docker container

```shell
# create network for eru project
$ docker network create eru-network
# add postgres container to eru-network
$ docker network connect eru-network postgres
# pull maven image
$ docker pull maven:3.8.6-openjdk-18-slim
# pull jdk image
$ docker pull openjdk:18-alpine
# build eru-service container
$ docker build -t eru-service:1.0.0 .
# run eru-service container
$ docker run --net eru-network -p8069:8069 --name eru-service -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/eru-service-prd -d eru-service:1.0.0
```
