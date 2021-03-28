/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author chocolate
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_peminjaman")
public class Peminjaman {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pinjaman", length = 36)
    private String id_pinjaman;

    @ManyToOne
    @JoinColumn(name = "anggota_id", insertable = false, updatable = false)
    private Anggota anggota;
    @Column(name = "anggota_id", nullable = false, length = 36)
    private String anggota_id;

    @ManyToOne
    @JoinColumn(name = "buku_id", insertable = false, updatable = false)
    private Buku buku;
    @Column(name = "buku_id", nullable = false, length = 36)
    private String buku_id;

    @OneToMany(mappedBy = "peminjaman")
    private List<Pengembalian> pengembalianList;


    @Column(name = "tanggal_pinjam", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_pinjam;

    @Column(name = "tanggal_kembali", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_kembali;

}
