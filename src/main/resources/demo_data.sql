insert into users (name, username, password)
VALUES ('Ivan', 'ivan@mail.ru', '$2a$12$0C4lu1W8hDpEHCKyyaoW6OLQcOy60rXDcysob.911.88zfNCCW/4W'),
       ('petr', 'petr@mail.ru', '$2a$12$0C4lu1W8hDpEHCKyyaoW6OLQcOy60rXDcysob.911.88zfNCCW/4W');

insert into tasks (title, description, status, expiration_date)
values ('Купить еды', null, 'TODO', '2023-01-10 12:00:00'),
        ('Сделать уроки', 'Java', 'IN_PROGRESS', '2023-01-20 15:00:00'),
        ('Убраться дома', null, 'DONE', null),
        ('Сходить на тренировку', 'Колизей', 'TODO', '2024-01-01 05:00:00');

insert into users_tasks (task_id, user_id)
values (1,2),
        (2,2),
        (3,2),
        (4,1);

insert into users_roles (user_id, role)
values (1, 'ROLE_USER'),
        (1, 'ROLE_ADMIN'),
        (2, 'ROLE_USER');
