DROP TABLE IF EXISTS Users;
CREATE TABLE IF NOT EXISTS Users
(
    id bigint auto_increment
        primary key,
    email    varchar(255)  not null,
    name     varchar(255)  not null,
    password varchar(1024) not null,
    constraint UK_5f7entu3leiopi9401fr7mxav
        unique (name)
);
INSERT INTO Users (name, email, password)
VALUES ('user1', 'u1@mail.ru', 'pass1_!U1'),
       ('user2', 'u2@mail.ru', 'pass2_!U2'),
       ('user3', 'u3@mail.ru', 'pass3_!U3'),
       ('user4', 'u4@mail.ru', 'pass4_!U4');
DROP TABLE IF EXISTS Products;

CREATE TABLE IF NOT EXISTS Products
(
    id  bigint auto_increment
        primary key,
    price double       not null,
    title varchar(255) not null unique
);
INSERT INTO Products(title, price)
VALUES ('Milk', 48.0),
       ('Bread', 25.5),
       ('Eggs', 98.9),
       ('Carrot', 33.5);