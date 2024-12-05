INSERT IGNORE INTO users (id, age, email, lastname, name, password)
VALUES (1, 30, 'admin@mail.ru', 'admin', 'admin', '$2a$12$im/KwQaC6Ia.Dj9BiFYmxeSh1ClnDt8YAGnHYqieTa.8Jn7dhp4Kq');

INSERT IGNORE INTO users (id, age, email, lastname, name, password)
VALUES (2, 30, 'user@mail.ru', 'user', 'user', '$2a$12$oUhGWuB/CEiikZj/z1wxSeIh9G1MEG3H0SV7M7XbrD8iI/CynasC.');

INSERT IGNORE INTO roles (id, name) VALUES (1, 'ADMIN');

INSERT IGNORE INTO roles (id, name) VALUES (2, 'USER');

INSERT IGNORE INTO users_roles (user_id, roles_id) VALUES (1,1);

INSERT IGNORE INTO users_roles (user_id, roles_id) VALUES (2,2);


