# CodeBrain Challenge

---

This project contains the API and the frontend application to CodeBrain Challenge.

## Requirements

---

- Docker - with docker compose installed

**OR**

- Java 11+
- MySQL 8+
- NodeJS

## Running

---
You can run this project using Docker containers - started with a single command using Docker Compose - or using a
development machine with Java, MySQL and NodeJS installed.

### Using Docker compose

```shell
docker-compose up
```

### Using a development machine

#### Start API

```shell
cd api
./gradlew run # Or `gradlew.bat run` on Windows
```

#### Start frontend

```shell
cd front
npm i
npm run dev
```

