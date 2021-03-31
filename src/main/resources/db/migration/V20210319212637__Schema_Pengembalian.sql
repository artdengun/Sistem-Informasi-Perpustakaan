CREATE TABLE t_pengembalian(
        id_kembali varchar(36) primary key unique,
        anggotaid varchar(100) not null,
        pinjamanid varchar(100) not null,
        terlambat varchar(100) not null,
        jumlah_denda decimal(18, 3) not null,
        foreign key(pinjamanid) references t_peminjaman(id_pinjaman),
        foreign key(anggotaid) references t_anggota(id_anggota)
);
