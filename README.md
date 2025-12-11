# drone-delivery-backend

This repository contains a modular-monolith scaffold for the Drone Delivery Management assessment.

API base: `http://localhost:8080/api/v1` (context path set in `application.yml`)

Quick start

 - Build the project (skip tests for a fast build):

```powershell
.\mvnw -DskipTests=true package
java -jar target\drone-delivery-backend-0.0.1-SNAPSHOT.jar
```

Note: the Maven wrapper was removed from this repository. Please ensure Maven is installed locally and the `mvn` command is available on your PATH.

 - Run tests:

```powershell
.\mvnw test
```

Postman

 - Import `postman_collection.json` into Postman to exercise the API. The collection includes requests to issue a token, list drones and send a heartbeat. Use the `token` environment variable to store the `Bearer` token.

What I changed and next steps

 - Implemented a simple JWT-like token issuer (`TokenServiceImpl`) including `sub` and `type` claims.
 - Added unit tests for token issuer and a plain unit test for `AuthController`.
 - Added a minimal in-memory `drones` skeleton (DTO, repo, controller) and a Postman collection.

Planned next changes (order):

 1. Implement orders module and core business logic (reservations, handoff jobs).
 2. Add security filter to validate tokens and enforce role-based access.
 3. Expand tests to cover orders and job flows + integration tests.
