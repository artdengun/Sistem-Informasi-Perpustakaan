package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Data
@Entity
public class AuditLog {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_pengguna_asli")
    private User penggunaAsli;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_pengguna_dipakai")
    private User penggunaDiPakai;

    @NotNull
    private LocalDateTime waktuKegiatan;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String keterangan;
}
