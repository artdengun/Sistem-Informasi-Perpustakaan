CREATE TABLE t_buku(
    id_buku     varchar (36) primary key unique,
    judul       varchar (50) not null,
    pengarang   varchar (50) not null,
    penerbit    varchar (50) not null,
    jumlah      varchar (50) not null
);
