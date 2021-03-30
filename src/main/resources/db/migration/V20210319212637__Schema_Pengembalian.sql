CREATE TABLE t_pengembalian(
        id_kembali varchar(36) primary key unique,
        anggotaid varchar(100),
        pinjamanid varchar(100),
        terlambat varchar(100) not null,
        jumlah_denda decimal(18, 3),
        foreign key(pinjamanid) references t_peminjaman(id_pinjaman),
        foreign key(anggotaid) references t_anggota(id_anggota)
);


