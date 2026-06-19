ALTER TABLE tools
    ADD COLUMN employee_id BIGINT DEFAULT NULL;

ALTER TABLE tools
    ADD CONSTRAINT fk_tools_employee
        FOREIGN KEY (employee_id)
            REFERENCES employees (id)
            ON DELETE SET NULL;