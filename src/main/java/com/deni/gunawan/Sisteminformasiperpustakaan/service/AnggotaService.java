package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AnggotaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnggotaService {

    @Autowired private AnggotaRepository anggotaRepository;

    public List<Anggota> getAnggotaList(){
        return anggotaRepository.findAll();
    }

    public void save(Anggota anggota){
        anggotaRepository.save(anggota);
    }

    public Optional<Anggota> findById(String id){
        return anggotaRepository.findById(id);
    }

    public void delete(String id){
        anggotaRepository.deleteById(id);
    }

}
