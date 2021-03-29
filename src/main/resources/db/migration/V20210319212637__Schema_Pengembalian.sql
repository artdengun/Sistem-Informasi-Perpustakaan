CREATE TABLE t_pengembalian(
        id_kembali varchar(36) primary key unique,
        pinjaman_id varchar(36),
        tanggal_kembali date,
        terlambat varchar(10),
        jumlah_denda decimal(10, 2) not null default 0,
        foreign key(pinjaman_id) references t_peminjaman(id_pinjaman)
);


