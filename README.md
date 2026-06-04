## Back-End URL:     <https://uspto-gov-patent-status-api.onrender.com/api/applications>

Front-End repo:      <https://github.com/WebDevJ/uspto.gov-application-review-ui.git>
Deployed Front-end:  <https://uspto-gov-application-review-ui.onrender.com>

# Patent Application Status API

## Purpose

Patent Application Status API is a Spring Boot REST microservice for searching and managing patent-style application records.

## Problem

Internal users need a reliable backend API to retrieve application status, filing information, applicant details, assigned unit information, and workflow state.

## Solution

This project uses a layered Spring Boot architecture with REST endpoints, DTOs, validation, global exception handling, JPA repository access, H2 database support, automated tests, Docker, and Docker Compose.

## Architecture

Controller → Service → Repository → Database

## Key Design Decisions

* Controller handles HTTP concerns only.
* Service layer owns business logic.
* Repository layer isolates persistence logic.
* DTOs protect the API contract from database changes.
* Validation catches bad requests before persistence.
* Global exception handling standardizes API errors.
* H2 enables fast local development.
* Docker makes the service portable and deployable.
* Docker Compose allows the service to be built and run with one command.
* The application uses a dynamic port configuration so it can run locally and on cloud platforms like Render.

## API Endpoints

| Method | Endpoint                                | Purpose                       |
| ------ | --------------------------------------- | ----------------------------- |
| GET    | `/api/applications`                     | Get all applications          |
| GET    | `/api/applications?status=PENDING`      | Filter applications by status |
| GET    | `/api/applications/{applicationNumber}` | Get one application           |
| POST   | `/api/applications`                     | Create a new application      |

## Example POST Request

```json
{
  "applicationNumber": "US-2026-0004",
  "title": "Digital Case Routing System",
  "applicantName": "James R. Roberts II",
  "status": "PENDING",
  "filingDate": "2026-04-01",
  "assignedUnit": "Tech Center 3700"
}
```

## Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Bean Validation
* H2 Database
* Maven
* JUnit
* Mockito
* Docker
* Docker Compose

## Application Port Configuration

In `src/main/resources/application.yaml`, the application uses this configuration:

```yaml
server:
  port: ${PORT:8080}
```

This means the application will use the `PORT` environment variable when one is provided.

If no `PORT` environment variable exists, it defaults to:

```text
8080
```

This is useful because local development usually runs on:

```text
http://localhost:8080
```

But cloud platforms like Render often provide their own dynamic port using an environment variable named `PORT`.

This setup allows the same application to work both locally and in a deployed environment without changing the code.

## Local Development

Run the application directly with Maven:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Then test:

```text
http://localhost:8080/api/applications
```

## How to Test

```bash
./mvnw test
```

Windows:

```bash
mvnw.cmd test
```

## How to Build

```bash
./mvnw clean package
```

Windows:

```bash
mvnw.cmd clean package
```

## How to Run the JAR

```bash
java -jar target/patent-status-api-0.0.1-SNAPSHOT.jar
```

## Docker

This project includes a `Dockerfile` so the Spring Boot application can be packaged and run as a container.

Manual Docker build:

```bash
docker build -t patent-status-api .
```

Manual Docker run:

```bash
docker run -p 8080:8080 patent-status-api
```

## Docker Compose

This project also includes Docker Compose so the service can be built and run with one command.

Run:

```bash
docker compose up --build
```

This command builds the Docker image and starts the container.

Stop the service:

```bash
docker compose down
```

Docker Compose also gives the container a predictable name instead of letting Docker generate a random name.

Example `docker-compose.yml`:

```yaml
services:
  patent-status-api:
    container_name: james-roberts-patent-api
    image: patent-status-api:latest
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
```

## Docker Compose Design Decision

I added Docker Compose so the service can be built and run with one command instead of manually building and running the container separately. It also gives the container a predictable name and makes the local deployment repeatable.

## H2 Console

Open:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:patentdb
Username: sa
Password: leave blank
```

Example query:

```sql
SELECT * FROM APPLICATION_RECORDS;
```

## Render Deployment Readiness

This project is ready to be deployed on Render because the application supports dynamic port binding:

```yaml
server:
  port: ${PORT:8080}
```

Render provides the port through the `PORT` environment variable. The application reads that value automatically.

If the app is running locally, it uses port `8080`.

If the app is running on Render, it uses the port Render assigns.

That makes the same Docker image work in both environments.

## Deployment

For the demo deployment, I used Docker to containerize the Spring Boot API and Docker Compose to make the local deployment repeatable. I also configured the application to use `server.port=${PORT:8080}` so it can run locally on port `8080` while also supporting cloud platforms like Render that assign a dynamic port through an environment variable. This shows that the service is not only coded correctly, but also prepared for real deployment outside of my local machine.

## Talking Points

This project demonstrates backend microservice structure, REST API design, DTO usage, validation, global exception handling, database access, testability, and containerized deployment readiness.

I structured the backend with a clean controller-service-repository pattern. The controller only handles HTTP concerns, the service owns business rules, and the repository isolates persistence. I used DTOs so the API contract stays stable even if the database model changes. That matters in government systems because reliability, maintainability, and clear contracts are more important than clever code.

I added Docker Compose so the service can be built and run with one command instead of manually building and running the container separately. It also gives the container a predictable name and makes the local deployment repeatable.

I also configured the server port using `${PORT:8080}` so the application can run locally on port `8080`, but also run correctly on cloud hosting platforms like Render where the runtime port is provided through an environment variable.

## Explanation

For Day 1, I built a Patent Application Status API as a Spring Boot microservice. The goal was to model a government-style application workflow where users can retrieve application records, filter by status, and create new records.

I used a layered architecture: the controller handles HTTP requests, the service layer owns the business logic, and the repository layer handles persistence through Spring Data JPA.

I used DTOs to keep the API contract separate from the entity model, which makes the system easier to maintain if the database changes later. I also added validation so bad requests fail early, and I added a global exception handler so API errors are consistent and predictable for frontend consumers.

For local development, I used H2 so the project can run quickly without external database setup, but the configuration can be swapped for PostgreSQL or Oracle through environment-based settings.

I added JUnit and MockMvc tests to cover service and controller behavior. Finally, I packaged the service with Docker and added Docker Compose so the API can be built and run with one repeatable command. I also configured the app to use `${PORT:8080}` so it can run locally and on deployment platforms like Render.

The main thing this project demonstrates is that I can build a clean, testable, maintainable backend service that an Angular frontend or another system can reliably consume.

