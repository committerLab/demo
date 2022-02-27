CREATE TABLE user(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(128),
    address VARCHAR(128),
    country VARCHAR(80),
    zip_code VARCHAR(10),
    birth_date DATE,
    PRIMARY KEY (id)
);