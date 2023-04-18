CREATE TABLE users(
    id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(100),
    country VARCHAR(50),
    zip_code VARCHAR(10));

CREATE TABLE post(
                        id INT PRIMARY KEY,
                        title VARCHAR(50) NOT NULL,
                        content VARCHAR(200) NOT NULL,
                        user_id INT NOT NULL
);

ALTER TABLE post
    ADD FOREIGN KEY (user_id) REFERENCES users(id);