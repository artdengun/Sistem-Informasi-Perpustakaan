package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Pengembalian;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.PengembalianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PengembalianService {

    @Autowired private PengembalianRepository pengembalianRepository;


    public List<Pengembalian> getPengembalianList(){
        return pengembalianRepository.findAll();
    }

    public void save(Pengembalian pengembalian){
        pengembalianRepository.save(pengembalian);
    }

    public Optional<Pengembalian> findById(String id){
        return pengembalianRepository.findById(id);
    }

    public void delete(String id){
        pengembalianRepository.deleteById(id);
    }
}
