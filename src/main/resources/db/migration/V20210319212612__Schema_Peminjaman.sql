CREATE TABLE t_peminjaman(
        id_pinjaman varchar(36) primary key NOT NULL DEFAULT '0',
        nama varchar(200),
        judul varchar(200),
        tanggal_pinjam date,
        tanggal_kembali date,
        FOREIGN KEY (nama) REFERENCES t_anggota(id_anggota) ON DELETE RESTRICT,
        FOREIGN KEY (judul) REFERENCES t_buku(id_buku) ON DELETE CASCADE
);

