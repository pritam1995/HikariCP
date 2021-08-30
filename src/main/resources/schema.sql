DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id          INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255),
    email       VARCHAR(255),
    created_date DATE,
    PRIMARY KEY (id)
);