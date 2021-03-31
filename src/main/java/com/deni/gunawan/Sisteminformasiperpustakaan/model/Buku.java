/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_buku")
public class Buku implements Serializable {

     @Id @GeneratedValue(generator = "uuid" )
     @GenericGenerator(name = "uuid", strategy = "uuid2")
     @Column(name = "id_buku")
     private String id_buku;

     @Column(name = "judul", nullable = false, length = 150)
     private String judul;

     @Column(name = "pengarang", nullable = false, length = 150)
     private String pengarang;

     @Column(name = "penerbit", nullable = false, length = 150)
     private String penerbit;

     @Column(name = "jumlah", length = 150, nullable = false)
     private String jumlah;

     @JsonManagedReference
     @OneToMany(mappedBy = "buku", fetch = FetchType.EAGER)
     private List<Peminjaman> peminjamanList;

}
