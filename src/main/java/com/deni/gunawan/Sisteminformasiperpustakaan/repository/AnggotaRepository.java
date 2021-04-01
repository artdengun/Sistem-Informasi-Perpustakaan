package com.deni.gunawan.Sisteminformasiperpustakaan.repository;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnggotaRepository extends JpaRepository<Anggota, String> {

    List<Anggota> findByUser(User p);


}
