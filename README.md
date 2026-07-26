# Typeracer

Multiplayer real-time typing race game built with Java 17 + Spring Boot.

## Tech Stack
- Java 17 + Spring Boot
- Spring WebSocket + STOMP
- Spring Security + JWT
- PostgreSQL + JPA
- HTML/JS Frontend

## Features
- JWT Authentication
- Real-time multiplayer via WebSockets
- Up to 4 players per room
- Live progress tracking
- Server-side progress validation

## Endpoints
- `POST /api/v1/auth/register` — Register
- `POST /api/v1/auth/login` — Login
- `POST /api/v1/room/create` — Create room
- `POST /api/v1/room/join/{code}` — Join room
- `WS /ws` — WebSocket connection

## Run locally
```bash
./mvnw spring-boot:run
```
