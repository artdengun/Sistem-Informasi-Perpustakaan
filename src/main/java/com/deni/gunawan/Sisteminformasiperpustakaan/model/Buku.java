/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author chocolate
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_buku")
public class Buku {

     @Id
     @GeneratedValue(generator = "uuid")
     @GenericGenerator(name = "uuid", strategy = "uuid2")
     @Column(name = "id_buku", length = 36)
     private String id_buku;

     @Column(name = "judul", nullable = false, length = 50)
     private String judul;

     @Column(name = "pengarang", nullable = false, length = 50)
     private String pengarang;

     @Column(name = "penerbit", nullable = false, length = 50)
     private String penerbit;

     @Column(name = "jumlah", length = 50, nullable = false)
     private String jumlah;

     @OneToMany(mappedBy = "buku")
     private List<Peminjaman> peminjamanList;

}
