# Formula 1 Betting Service

Backend REST API for listing F1 events, placing bets, and processing event outcomes. Built with Spring Boot and Maven.

## Prerequisites

- Java 18+
- Maven 3.9+
- macOS terminal or IntelliJ IDEA 2025.2.5

## Project Structure

- Source: `src/main/java/...`
- Config: `src/main/resources/application.properties`
- Tests: `src/test/java/...`

## Configuration

Adjust properties in `src/main/resources/application.properties`:
- Server: `server.port`, `server.address`
- External API: `f1.api.base-url`
- Business rules: `f1.user.initial-balance`, `f1.betting.*`

Default server runs on `http://localhost:8080`.

## Build and Run

- Terminal (macOS):
    - `mvn clean install`
    - `mvn spring-boot:run`
- IntelliJ IDEA:
    - Open project `f1`
    - Run `Main` class or use Maven run configuration

## API Endpoints

Base path: `/api`

- List events: `GET /api/events?year={year}&sessionType={type}&country={country}`
    - All filters are optional.
- Place bet: `POST /api/bets`
    - Body: `{"userId":"<uuid>","eventId":"<session_key>","driverId":"<driver_number>","amount":<number>}`
- Process outcome: `POST /api/outcomes`
    - Body: `{"eventId":"<session_key>","winningDriverId":"<driver_number>"}`

Notes:
- Odds in driver market are random in \[2,3,4\].
- Each user starts with 100 EUR.
- In-memory storage (no external DB).

## Example Usage (curl)

- List events:
    - `curl "http://localhost:8080/api/events?year=2025&sessionName=Sprint&country=Belgium"`
- Place bet:
    - `curl -X POST "http://localhost:8080/api/bets" -H "Content-Type: application/json" -d '{"userId":"11111111-1111-1111-1111-111111111111","eventId":"123","driverId":"44","amount":20}'`
- Process outcome:
    - `curl -X POST "http://localhost:8080/api/outcomes" -H "Content-Type: application/json" -d '{"eventId":"123","winningDriverId":"44"}'`

## Run Tests

- `mvn test`
- Test classes are under `src/test/java/org/example/...`

## Extensibility

External API integration is abstracted via `F1EventProvider`. Add new providers without changing business logic.