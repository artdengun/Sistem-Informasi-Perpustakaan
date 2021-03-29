/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author chocolate
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_pengembalian")
public class Pengembalian implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_kembali")
    private String id_kembali;

    @Column(name = "tanggal_kembali", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_kembali;

    @Column(name = "terlambat", length = 10)
    private String terlambat;

    @Column(name = "jumlah_denda", nullable = false)
    private BigDecimal jumlah_denda;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pinjaman_id", insertable = false, updatable = false)
    private Peminjaman peminjaman;
    @Column(name = "pinjaman_id", length = 36)
    private String pinjaman_id;
}

