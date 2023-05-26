CREATE TABLE users (
        id BIGSERIAL PRIMARY KEY,
        login TEXT NOT NULL,
        password TEXT NOT NULL
);
CREATE INDEX login_idx ON users (login);