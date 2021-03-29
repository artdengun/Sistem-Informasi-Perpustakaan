CREATE TABLE t_pengembalian(
        id_kembali varchar(36) primary key unique,
        anggotaid varchar(100),
        pinjamanid varchar(100),
        tanggal_kembali DATE,
        terlambat varchar(100),
        jumlah_denda decimal(10, 2) not null default 0,
        foreign key(pinjamanid) references t_peminjaman(id_pinjaman),
        foreign key(anggotaid) references t_anggota(id_anggota)
);


