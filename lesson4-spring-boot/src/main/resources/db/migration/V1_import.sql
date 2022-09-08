DROP TABLE IF EXISTS users;
create table IF NOT EXISTS users
(
    id    bigint auto_increment
        primary key,
    name  varchar(255) not null,
    email varchar(255) not null,
        password varchar (1024) not null,
    constraint UK_5f7entu3leiopi9401fr7mxav
        unique (name),
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products
(
    id      bigint auto_increment
        primary key,
    price   double       not null,
    title   varchar(255) not null,
    constraint UK_av7t3nxp5jjjcuf9x0a7u3ygl
        unique (title)

);



DROP TABLE IF EXISTS Contacts;
create table if not exists Contacts
(
    id      bigint auto_increment
        primary key,
    type    varchar(255) not null,
    value   varchar(255) not null,
    user_id bigint       not null,
    constraint FK26y1n6nbfwxkkya36l6rwe7bi
        foreign key (user_id) references users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT  NOT NULL,
    user_id    BIGINT  NOT NULL,
    price      DECIMAL NOT NULL,
    date       DATETIME,
    quantity   BIGINT  NOT NULL,
    CONSTRAINT FK_userid
        FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_product_id
        FOREIGN KEY (product_id) REFERENCES products (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO users (name, email, password)
VALUES ('user1', 'u1@e.ru', 'pass1_!U1'),
       ('user2', 'u2@e.ru', 'pass2_!U2'),
       ('user3', 'u3@e.ru', 'pass3_!U3'),
       ('user4', 'u4@e.ru', 'pass4_!U4');

INSERT INTO contacts (type, value, user_id)
VALUES ('EMAIL', 'u1@e.ru', 1),
       ('MOBILE_PHONE', '+7(111)111-11-11', 1),
       ('WORK_PHONE', '222-22-22', 1),
       ('MOBILE_PHONE', '+7(111)222-22-22', 2),
       ('WORK_PHONE', '111-11-11', 2),
       ('MOBILE_PHONE', '+7(111)111-22-22', 3);

INSERT INTO products(title, price)
VALUES ('Milk', 48.0),
       ('Bread', 25.5),
       ('Eggs', 98.9),
       ('Carrot', 33.5),
       ('Flour', 88.6);


INSERT INTO orders (product_id, user_id, price, date, quantity)
VALUES (1, 1, 12.0, '22.03.2015', 1),
       (2, 1, 22.0, '22.03.2015', 3),
       (1, 3, 15.6, '22.03.2018', 1),
       (3, 3, 33.0, '22.03.2015', 2),
       (1, 1, 15.6, '22.03.2018', 1);
