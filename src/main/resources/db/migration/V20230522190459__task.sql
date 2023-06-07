DROP TABLE task;
CREATE TABLE task (
        id BIGSERIAL PRIMARY KEY,
        date DATE NOT NULL,
        description TEXT,
        done BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE INDEX task_date_idx ON task (date);
CREATE INDEX task_done_idx ON task (done);

DROP TABLE user_info;
CREATE TABLE user_info (
        id BIGSERIAL PRIMARY KEY,
        login TEXT NOT NULL,
        password TEXT NOT NULL,
        roles TEXT NOT NULL
);
CREATE INDEX login_idx2 ON user_info (login);