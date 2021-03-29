CREATE TABLE t_peminjaman(
        id_pinjaman varchar(36) primary key unique,
        anggotaid varchar(200),
        bukuid varchar(200),
        tanggal_pinjam DATE,
        tanggal_kembali DATE,
        FOREIGN KEY (anggotaid) REFERENCES t_anggota(id_anggota),
        FOREIGN KEY (bukuid) REFERENCES t_buku(id_buku)
);

