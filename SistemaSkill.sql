CREATE TABLE skills_table (
    skill_id SERIAL PRIMARY KEY,
    skill_name VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255) NOT NULL,
    image VARCHAR(255), NOT NULL
);

CREATE TABLE users_table (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    access_type INTEGER NOT NULL,
    CONSTRAINT users_table_access_type_check CHECK (access_type BETWEEN 0 AND 1)
);

CREATE TABLE user_skills_table (
    user_skill_id SERIAL PRIMARY KEY,
    level INTEGER NOT NULL DEFAULT 1,
    skill_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_skill FOREIGN KEY(skill_id) REFERENCES skills_table(skill_id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users_table(user_id) ON DELETE CASCADE
);
