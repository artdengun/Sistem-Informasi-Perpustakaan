insert into s_roles (id, nama)
values ('u001', 'user');

insert into s_roles (id, nama)
values ('s001', 'staff');

insert into s_roles (id, nama)
values ('a001', 'admin');

insert into s_user (id, firstname, lastname,  username, email, password, active,  id_role)
values ('usr001', 'deni','gunawan','artdengun','user775@gmail.com','$2y$12$s0hZsPm63eQcrOTSaVbRuuseJcYgrIF1KLhvw3.ZVAt4grNdGI4Gi ', true, 'u001');

insert into s_user (id, firstname, lastname,  username, email, password, active,  id_role)
values ('stf002', 'deni','gunawan','artdengun','staf775@gmail.com','$2y$12$/W2/7lRDQKA53cg9iHAeQO5/TfDNhIHNCBDtbgpenLOFQcXWZonwi ', true, 's001');

insert into s_user (id, firstname, lastname,  username, email, password, active,  id_role)
values ('adm003', 'deni','gunawan','artdengun','admin775@gmail.com','$2y$12$z32qWkJRuuJ6mbUTOD8U7eA2hHYoiPGGqbd/Cy/eJs7V4Qu7j6FA6 ', true, 'a001');


insert into s_permissions (id, label, value)
values ('p001', 'Lihat Data Perpustakaan', 'USER');

insert into s_permissions (id, label, value)
values ('p002', 'Mengatur Data', 'STAFF');

insert into s_permissions (id, label, value)
values ('p003', 'Mengelola Aplikasi Perpustakaan', 'ADMIN');


insert into s_roles_permissions (id_role, id_permission)
values ('u001', 'p001');

insert into s_roles_permissions (id_role, id_permission)
values ('s001', 'p002');

insert into s_roles_permissions (id_role, id_permission)
values ('a001', 'p003');