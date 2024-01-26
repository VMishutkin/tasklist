insert into users (name, username, password)
VALUES ('Ivan', 'ivan', '$2a$12$0C4lu1W8hDpEHCKyyaoW6OLQcOy60rXDcysob.911.88zfNCCW/4W'),
       ('Petr', 'petr', '$2a$12$0C4lu1W8hDpEHCKyyaoW6OLQcOy60rXDcysob.911.88zfNCCW/4W');
       ('admin', 'admin', '$2a$12$0C4lu1W8hDpEHCKyyaoW6OLQcOy60rXDcysob.911.88zfNCCW/4W');

insert into tasks (title, description, status, creation_date,user_id)
values ('Купить еды', null, 'TODO', '2023-01-10 12:00:00', 1),
        ('Сделать уроки', 'Java', 'IN_PROGRESS', '2023-01-20 15:00:00', 2),
        ('Убраться дома', null, 'DONE', null, 1),
        ('Сходить на тренировку', 'Колизей', 'TODO', '2024-01-01 05:00:00', 3);


insert into users_roles (user_id, role)
values (3, 'ROLE_USER'),
        (3, 'ROLE_ADMIN'),
        (2, 'ROLE_USER'),
        (1, 'ROLE_USER');
