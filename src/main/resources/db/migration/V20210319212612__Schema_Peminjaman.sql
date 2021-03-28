CREATE TABLE t_peminjaman(
        id_pinjaman varchar(36) primary key unique,
        anggota_id varchar(36),
        buku_id varchar(36),
        tanggal_pinjam date,
        tanggal_kembali date,
        FOREIGN KEY (anggota_id) REFERENCES t_anggota(id_anggota),
        FOREIGN key(buku_id) REFERENCES t_buku(id_buku)
);
