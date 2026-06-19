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
- SpringDoc OpenAPI (Swagger)

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

```
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
                ├── application.properties
                └── db/
                    └── migration/
                        └── V1__create_tools_table.sql
```

---

## Backend Architecture

```
src/main/java/com/grupoeragin/inventory/
├── controllers/   # REST endpoints
├── services/      # Business logic
├── repositories/  # Data access (Spring Data JPA)
├── entities/      # JPA entities (database tables)
│   └── enums/     # Entity enums (e.g. ToolStatus)
└── dtos/          # Data Transfer Objects
```

---

## Infrastructure

### Docker Services

| Service          | Image          | Port      | Purpose          |
|-------------------|----------------|-----------|------------------|
| eragin-db        | postgres:16    | 5432:5432 | Primary database |
| eragin-pgadmin   | dpage/pgadmin4 | 5050:80   | Database GUI     |

### Commands
```bash
# Start infrastructure
docker compose up -d

# Stop infrastructure
docker compose down

# Full reset (deletes all data)
docker compose down -v
docker compose up -d
```

---

## Database

- **Engine**: PostgreSQL 16
- **Host**: localhost:5432
- **Database**: eragin_db
- **User**: eragin_user
- **Migrations**: Flyway (runs automatically on startup)
- **Schema management**: Flyway owns DDL — Hibernate set to `validate` only

### Tables
| Table | Migration | Description |
|-------|-----------|--------------|
| tools | V1 | Industrial tools inventory |

### Entities

| Entity | Table | Package |
|--------|-------|---------|
| Tool | tools | `entities` |

### Enums

| Enum | Values | Used in |
|------|--------|---------|
| ToolStatus | AVAILABLE, IN_USE, MAINTENANCE, LOST | `Tool.status` |

### Technical Notes
- **Spring Boot 4.x + Flyway**: requires explicit `spring-boot-starter-flyway`
  dependency. Adding only `flyway-core` + `flyway-database-postgresql` is NOT
  enough — Spring Boot 4 won't autoconfigure Flyway without the starter.
- **Migration execution order**: `spring.jpa.defer-datasource-initialization=true`
  ensures Flyway runs before Hibernate validates the schema.
- **New tools always start as `AVAILABLE`**: the client cannot set `status` on
  creation — it's a business rule enforced in `ToolService`, not a client decision.
- **`ddl-auto=none` vs `validate`**: In Spring Boot 4.1.0, using `validate` causes  
  Hibernate to run schema validation before Flyway executes migrations, crashing  
  startup. Setting `ddl-auto=none` is the correct production approach — Flyway owns  
  the schema entirely, Hibernate trusts it without validating.
- **Flyway script immutability**: Never modify a `V*.sql` file after it has been  
  applied. Flyway stores a checksum per migration in `flyway_schema_history`. Any  
  change — even whitespace — causes a `checksum mismatch` error on next startup.  
  To fix changes, always create a new versioned migration.
- **Full reset procedure** (when `flyway_schema_history` is corrupted or  
  checksums are stale):  
  `docker compose down -v && docker compose up -d`
---

## How to Run

### Prerequisites
- Docker Desktop running
- Java 21
- Maven

### Steps
```bash
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
| http://localhost:8080/swagger-ui.html | API documentation (Swagger) |
| http://localhost:5050 | pgAdmin (DB GUI) |

---

## API Documentation

Swagger UI available at: `http://localhost:8080/swagger-ui.html`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|--------------|
| POST | /api/tools | Create a new tool |
| GET | /api/tools | List all tools |
| GET | /api/tools/{id} | Get a tool by ID |

---

## Weekly Progress

| Week | Focus | Status |
|------|-------|--------|
| 1 | Docker + Spring Boot setup + Flyway + Tool CRUD (Create/Read) + Swagger | ✅ Done |

---

## Roadmap

- [x] Docker Compose with PostgreSQL + pgAdmin
- [x] Spring Boot project structure
- [x] Database connection configured
- [x] Flyway first migration
- [x] Tool entity (JPA)
- [x] Tool repository + service + controller
- [x] Swagger / OpenAPI documentation
- [ ] Angular frontend _(Phase 2)_
- [ ] Global exception handling (`@ControllerAdvice`) _(Week 2)_
- [ ] Update / Delete endpoints for Tool _(Week 2)_