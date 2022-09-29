CREATE TABLE authorities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    authority varchar(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (user_id) REFERENCES users(id)
)
