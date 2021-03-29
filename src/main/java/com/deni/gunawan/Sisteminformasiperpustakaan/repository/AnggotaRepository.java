package com.deni.gunawan.Sisteminformasiperpustakaan.repository;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnggotaRepository extends JpaRepository<Anggota, String> {


}
