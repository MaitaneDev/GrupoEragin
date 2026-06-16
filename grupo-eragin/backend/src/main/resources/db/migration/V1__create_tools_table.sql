CREATE TABLE tools (
                       id BIGSERIAL PRIMARY KEY,
                       serial_number VARCHAR(100) NOT NULL UNIQUE,
                       name VARCHAR(200) NOT NULL,
                       brand VARCHAR(100) NOT NULL,
                       model VARCHAR(100) NOT NULL,
                       status VARCHAR(50) NOT NULL,
                       location VARCHAR(200),
                       purchase_date DATE,
                       created_at TIMESTAMP NOT NULL DEFAULT NOW()
);