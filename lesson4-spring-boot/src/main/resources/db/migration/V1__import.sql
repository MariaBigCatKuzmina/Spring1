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
    title   varchar(255) not null

);

DROP TABLE IF EXISTS contacts;
create table if not exists contacts
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

