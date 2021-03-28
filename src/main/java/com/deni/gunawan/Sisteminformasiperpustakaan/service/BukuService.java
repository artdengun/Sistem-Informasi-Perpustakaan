package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BukuService {

    @Autowired private BukuRepository BukuRepository;


    public List<Buku> getBukuList(){
        return BukuRepository.findAll();
    }

    public void save(Buku Buku){
        BukuRepository.save(Buku);
    }

    public Optional<Buku> findById(String id){
        return BukuRepository.findById(id);
    }

    public void delete(String id){
        BukuRepository.deleteById(id);
    }
}
