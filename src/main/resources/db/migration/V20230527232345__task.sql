ALTER TABLE user_info ADD CONSTRAINT login_constr UNIQUE (login);
ALTER TABLE task ADD COLUMN user_id BIGSERIAL REFERENCES user_info(id);
