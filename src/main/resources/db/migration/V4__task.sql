CREATE TABLE user_info (
        id BIGSERIAL PRIMARY KEY,
        login TEXT NOT NULL,
        password TEXT NOT NULL,
        roles TEXT NOT NULL
);
CREATE INDEX login_idx2 ON users (login);