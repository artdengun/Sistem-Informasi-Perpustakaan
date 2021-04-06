insert into roles (id, name)
values ('r001', 'user');

insert into roles (id, name)
values ('r002', 'staff');


insert into users (id, firstname, lastname,  username, email, password, active,  id_role)
values ('u001', 'deni','gunawan','user','user@gmail.com','$2a$10$qA7ykwEwkB8grY3cUcF9hOSROEJm54PCmiuxzACXKda6sjt3T7.LW', true, 'r001');

insert into users (id, firstname, lastname,  username, email, password, active,  id_role)
values ('u002', 'deni','gunawan','staff','denigunawan775@gmail.com','$2a$10$nUQQNW8tQngd.Bsrxnv4euV7tK/vu/oQ57rnkPvetVV6QjPgAt/o2', true, 'r002');


insert into s_permissions (id, label, value)
values ('p001', 'Lihat Data Perpustakaan', 'USER');

insert into s_permissions (id, label, value)
values ('p002', 'Mengatur Data', 'STAFF');

insert into users_roles (id_user,id_role, id_permission)
values ('u001', 'r001','p001');


insert into users_roles (id_user,id_role, id_permission)
values ('u002', 'r002','p001');


insert into users_roles (id_user,id_role, id_permission)
values ('u002', 'r002','p002');

