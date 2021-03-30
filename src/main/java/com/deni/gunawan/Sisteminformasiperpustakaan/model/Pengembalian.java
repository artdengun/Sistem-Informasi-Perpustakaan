/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anggotaid", insertable = false, updatable = false)
    private Anggota anggota;
    @Column(name = "anggotaid", nullable = false, length = 200)
    private String anggotaid;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pinjamanid", insertable = false, updatable = false)
    private Peminjaman peminjaman;
    @Column(name = "pinjamanid")
    private String pinjamanid;

    @Column(name = "terlambat", length = 100)
    private String terlambat;

    @Column(name = "jumlah_denda",length = 100)
    private BigDecimal jumlah_denda;


}

