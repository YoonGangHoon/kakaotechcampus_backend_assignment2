DROP TABLE Schedule;
DROP TABLE Author;

CREATE TABLE IF NOT EXISTS Author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    createdAt DATE,
    modifiedAt DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Schedule (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    author_id BIGINT,
    password VARCHAR(255),
    createdAt DATE,
    modifiedAt DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES Author(id)
);