DROP TABLE Schedule;

CREATE TABLE IF NOT EXISTS Schedule (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    password VARCHAR(255),
    createdAt DATE,
    modifiedAt DATE,
    PRIMARY KEY (id)
);