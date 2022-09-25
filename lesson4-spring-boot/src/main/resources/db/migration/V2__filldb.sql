USE lesson7;

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
