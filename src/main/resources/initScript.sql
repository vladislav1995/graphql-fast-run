CREATE SCHEMA IF NOT EXISTS fast_run;

SET search_path TO fast_run;

CREATE TABLE IF NOT EXISTS fast_run.response(
    id SERIAL PRIMARY KEY,
    method VARCHAR(100) NOT NULL,
    status INT,
    message VARCHAR(100) NOT NULL,
    last_update_date DATE
);