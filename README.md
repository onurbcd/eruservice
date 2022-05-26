# Eru Service

## Postgres in Docker

```
docker run -p5432:5432 --name postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=admin -e POSTGRES_DB=eru-service-dev -d postgres:latest
```
