CREATE TABLE employees
(
    id         BIGSERIAL PRIMARY KEY,
    full_name  VARCHAR(200) NOT NULL,
    dni        VARCHAR(20)  NOT NULL UNIQUE,
    email      VARCHAR(150) NOT NULL UNIQUE,
    phone      VARCHAR(20)           DEFAULT NULL,
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW()
);