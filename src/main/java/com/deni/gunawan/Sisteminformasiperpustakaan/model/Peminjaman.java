/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author chocolate
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_peminjaman")
public class Peminjaman  implements Serializable {


    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_pinjaman")
    private String id_pinjaman;



    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anggotaid", insertable = false, updatable = false)
    private Anggota anggota;
    @Column(name = "anggotaid", nullable = false, length = 200)
    private String anggotaid;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bukuid", insertable = false, updatable = false)
    private Buku buku;
    @Column(name = "bukuid", nullable = false, length = 200)
    private String bukuid;


    @JsonManagedReference
    @OneToMany(mappedBy = "peminjaman", fetch = FetchType.EAGER)
    private List<Pengembalian> pengembalianList;

    @Column(name = "tanggal_pinjam")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tanggal_pinjam;

    @Column(name = "tanggal_kembali")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tanggal_kembali;

}
