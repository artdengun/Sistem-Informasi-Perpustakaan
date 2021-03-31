package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name ="role")
public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "role_id")
    private String id;
    private String role;


}