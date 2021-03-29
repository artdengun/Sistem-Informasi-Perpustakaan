CREATE TABLE t_buku(
    id_buku     varchar (36) primary key NOT NULL DEFAULT '0',
    judul       varchar (200) not null,
    pengarang   varchar (150) not null,
    penerbit    varchar (150) not null,
    jumlah      varchar (150) not null
);
