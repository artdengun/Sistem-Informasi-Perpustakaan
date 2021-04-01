insert into s_roles (id, name)
values ('mhs-001', 'mahasiswa');

insert into s_roles (id, name)
values ('adm-001', 'admin');

insert into s_roles (id, name)
values ('dvr-001', 'developer');

insert into s_users (id, username, active, id_role)
values ('mahasiswa', 'mahasiswa', true, 'mhs-001');

insert into s_users (id, username, active, id_role)
values ('admin', 'admin', true, 'adm-001');


insert into s_users (id, username, active, id_role)
values ('developer', 'developer', true, 'dvr-001');

-- password : mahasiswa
insert into s_users_passwords (id_user, password)
values ('mahasiswa', '$2a$10$QlX3csFnic/cJylMMTuoi.VJP2iS4UG/gqsXMF8wso50Yfd/g.D.W');

-- password : admin
insert into s_users_passwords (id_user, password)
values ('admin', '$2a$10$giNNBwdRDKsEHuMgf5jLKe3oLSnbnnaD8dYgGhi9CtS1aHFzBYOZ.');

-- password developer
insert into s_users_passwords (id_user, password)
values ('developer', '$2a$10$D2X8RJ3oGUtr/5LSbba2feWoem/EGHKbTEYEgpeSuEtYSHHlYQ7km');


insert into s_permissions (id, label, value)
values ('lihat-001', 'Lihat Data Perpustakaan', 'LIHAT_DATA_PERPUSTAKAAN');

insert into s_permissions (id, label, value)
values ('mengatur-001', 'Mengatur Data Perpustakaan', 'MENGATUR_DATA_PERPUSTAKAAN');

insert into s_permissions (id, label, value)
values ('mengelola-001', 'Mengelola Aplikasi Perpustakaan', 'MENGELOLA_APLIKASI_PERPUSTAKAAN');


insert into s_roles_permissions (id_role, id_permission)
values ('mhs-001', 'lihat-001');

insert into s_roles_permissions (id_role, id_permission)
values ('adm-001', 'lihat-001');

insert into s_roles_permissions (id_role, id_permission)
values ('adm-001', 'mengelola-001');

insert into s_roles_permissions (id_role, id_permission)
values ('dvr-001', 'lihat-001');

insert into s_roles_permissions (id_role, id_permission)
values ('dvr-001', 'mengatur-001');

insert into s_roles_permissions (id_role, id_permission)
values ('dvr-001', 'mengelola-001');