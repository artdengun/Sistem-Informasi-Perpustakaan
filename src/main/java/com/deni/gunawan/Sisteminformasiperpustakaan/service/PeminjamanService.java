package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Peminjaman;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired private PeminjamanRepository peminjamanRepository;


    public List<Peminjaman> getPeminjamanList(){
        return peminjamanRepository.findAll();
    }

    public void save(Peminjaman peminjaman){
        peminjamanRepository.save(peminjaman);
    }

    public Optional<Peminjaman> findById(String id){
        return peminjamanRepository.findById(id);
    }

    public void delete(String id){
        peminjamanRepository.deleteById(id);
    }
}
