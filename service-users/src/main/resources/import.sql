INSERT INTO `users` (username, password, enabled, name, surname, email) VALUES ('sergio', '12345', 1, 'Sergio', 'Romero', 'sergio@gmail.com');
INSERT INTO `users` (username, password, enabled, name, surname, email) VALUES ('admin', '12345', 1, 'Admin', 'Admin', 'admin@gmail.com');

INSERT INTO `roles` (name) values ('ROLE_USER');
INSERT INTO `roles` (name) values ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) values (1, 1);
INSERT INTO `users_roles` (user_id, role_id) values (2, 2);
INSERT INTO `users_roles` (user_id, role_id) values (2, 1);