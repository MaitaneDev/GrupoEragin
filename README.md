# Grupo Eragin — Industrial Tool & Inventory Control System

> Sistema de control de inventario de herramientas industriales para seguimiento
> por número de serie, gestión de entregas/devoluciones en obra y alertas de incidencias.

---

## Tech Stack

### Backend
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- Flyway (database migrations)
- Lombok
- Bean Validation

### Frontend
- Angular 22+ _(Phase 2)_
- Standalone Components
- Signal Forms
- Zoneless Change Detection

### Infrastructure
- Docker + Docker Compose
- PostgreSQL 16
- pgAdmin 4

---

## Project Structure
grupo-eragin/

├── docker-compose.yml

├── README.md

└── backend/

├── pom.xml

└── src/

└── main/

├── java/

│   └── com/grupoeragin/inventory/

└── resources/

└── application.properties

---

## Backend Architecture
src/main/java/com/grupoeragin/inventory/

├── controller/    # REST endpoints

├── service/       # Business logic

├── repository/    # Data access (Spring Data JPA)

├── entity/        # JPA entities (database tables)

└── dto/           # Data Transfer Objects

---

## Infrastructure

### Docker Services

| Service          | Image          | Port      | Purpose          |
|------------------|----------------|-----------|------------------|
| eragin-db        | postgres:16    | 5432:5432 | Primary database |
| eragin-pgadmin   | dpage/pgadmin4 | 5050:80   | Database GUI     |

### Start infrastructure
```bash
docker compose up -d
```

### Stop infrastructure
```bash
docker compose down
```

---

## Database

- **Engine**: PostgreSQL 16
- **Host**: localhost:5432
- **Database**: eragin_db
- **User**: eragin_user
- **Migrations**: Flyway (runs automatically on startup)
- **Schema management**: Flyway owns DDL — Hibernate set to `validate` only
- **Spring Boot 4.x + Flyway**: requires explicit `spring-boot-starter-flyway`
  dependency. Adding only `flyway-core` + `flyway-database-postgresql` is NOT
  enough — Spring Boot 4 won't autoconfigure Flyway without the starter.
- **Migration execution order**: `spring.jpa.defer-datasource-initialization=true`
  ensures Flyway runs before Hibernate validates the schema.
---

## How to Run

### Prerequisites
- Docker Desktop running
- Java 21
- Maven

### Steps
```bashaaa
# 1. Start the database
docker compose up -d

# 2. Run the backend
cd backend
./mvnw spring-boot:run
```

### Useful URLs
| URL | Purpose |
|-----|---------|
| http://localhost:8080 | Backend API |
| http://localhost:5050 | pgAdmin (DB GUI) |

---

## API Documentation

> Swagger UI will be available at: `http://localhost:8080/swagger-ui.html` _(Week 2)_

---

## Weekly Progress

| Week | Focus | Status |
|------|-------|--------|
| 1 | Docker + Spring Boot setup + Flyway + Tool entity | 🔄 In progress |

---

## Roadmap

- [x] Docker Compose with PostgreSQL + pgAdmin
- [x] Spring Boot project structure
- [x] Database connection configured
- [x] Flyway first migration
- [ ] Tool entity (JPA)
- [ ] Tool repository + service + controller
- [ ] Angular frontend _(Phase 2)_