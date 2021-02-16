INSERT INTO users (username, password, enabled, name, surname, email) VALUES ('sergio', '$2a$10$EsjycqOGJwEGduu44PEiIeLKMbTqKoRqFIDDWSu6hnHZ9ixF9PYKq', true, 'Sergio', 'Romero', 'sergio@gmail.com');
INSERT INTO users (username, password, enabled, name, surname, email) VALUES ('admin', '$2a$10$A77a96KjrjqLWhsb4o86C.4pyN4qecE3vXndJpYYPjy3LhH1pgz/.', true, 'Admin', 'Admin', 'admin@gmail.com');

INSERT INTO roles (name) values ('ROLE_USER');
INSERT INTO roles (name) values ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) values (1, 1);
INSERT INTO users_roles (user_id, role_id) values (2, 2);
INSERT INTO users_roles (user_id, role_id) values (2, 1);