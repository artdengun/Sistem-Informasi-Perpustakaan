insert into s_roles (id, name)
values ('m001', 'mahasiswa');

insert into s_roles (id, name)
values ('a001', 'admin');

insert into s_roles (id, name)
values ('d001', 'developer');

insert into s_users (id, username, active, id_role)
values ('u001', 'mahasiswa', true, 'm001');

insert into s_users (id, username, active, id_role)
values ('a001', 'admin', true, 'a001');

insert into s_users (id, username, active, id_role)
values ('d001', 'developer', true, 'd001');

-- password : mahasiswa
insert into s_users_passwords (id_user, password)
values ('u001', '$2a$10$QlX3csFnic/cJylMMTuoi.VJP2iS4UG/gqsXMF8wso50Yfd/g.D.W');

-- password : admin
insert into s_users_passwords (id_user, password)
values ('a001', '$2a$10$giNNBwdRDKsEHuMgf5jLKe3oLSnbnnaD8dYgGhi9CtS1aHFzBYOZ.');

-- password developer
insert into s_users_passwords (id_user, password)
values ('d001', '$2a$10$D2X8RJ3oGUtr/5LSbba2feWoem/EGHKbTEYEgpeSuEtYSHHlYQ7km');


insert into s_permissions (id, label, value)
values ('p001', 'Lihat Data Perpustakaan', 'LIHAT_DATA');

insert into s_permissions (id, label, value)
values ('p002', 'Mengatur Data Perpustakaan', 'ADMIN');

insert into s_permissions (id, label, value)
values ('p003', 'Mengelola Aplikasi Perpustakaan', 'DEVELOPER');


insert into s_roles_permissions (id_role, id_permission)
values ('m001', 'p001');

insert into s_roles_permissions (id_role, id_permission)
values ('a001', 'p002');


insert into s_roles_permissions (id_role, id_permission)
values ('d001', 'p002');

insert into s_roles_permissions (id_role, id_permission)
values ('d001', 'p003');