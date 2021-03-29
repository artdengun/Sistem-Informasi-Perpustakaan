/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author chocolate
 */


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_anggota")
public class Anggota {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_anggota")
    private String id_anggota;

    @Column(name = "nama", length = 200, nullable = false)
    private String nama;

    @Column(name = "nim", length = 50, nullable = false)
    private String nim;

    @Column(name = "jurusan", length = 50, nullable = false)
    private String jurusan;


    @Column(name = "angkatan", length = 50, nullable = false)
    private String angkatan;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @JsonManagedReference
    @OneToMany(mappedBy = "anggota", fetch = FetchType.EAGER)
    private List<Peminjaman> peminjamanList;

    @JsonManagedReference
    @OneToMany(mappedBy = "anggota", fetch = FetchType.EAGER)
    private List<Pengembalian> pengembalianList;


}
