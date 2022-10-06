CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE users_roles (
    users_id BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    PRIMARY KEY (users_id, roles_id),
    CONSTRAINT FK_user_id
        FOREIGN KEY (users_id) REFERENCES users(id),
    CONSTRAINT FK_roles_id
           FOREIGN KEY (roles_id) REFERENCES roles(id)
);

INSERT INTO roles(name)
VALUES ('ROLE_GUEST'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPER_ADMIN');