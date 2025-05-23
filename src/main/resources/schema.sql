CREATE TABLE IF NOT EXISTS Schedule (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    password VARCHAR(255),
    createdAt DATETIME,
    modifiedAt DATETIME,
    PRIMARY KEY (id)
);