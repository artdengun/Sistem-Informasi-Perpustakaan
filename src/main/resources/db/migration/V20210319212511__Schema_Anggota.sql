create table t_anggota(
    id_anggota  varchar  (36) primary key unique,
    nama        varchar  (200) not null,
    id_users     varchar  (36)  not null,
    nim         varchar  (50) not null,
    jurusan     varchar  (50) not null,
    angkatan    varchar  (50) not null,
    alamat      varchar       not null,
    foreign key (id_users) references s_user(id)
);
