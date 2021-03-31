package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AnggotDaoImpl;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AnggotaRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

@Service
public class AnggotaService {

    @Autowired private AnggotaRepository anggotaRepository;
    @Autowired
    private AnggotDaoImpl anggotDao;

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

    public JasperPrint exportPdfFile() throws SQLException, JRException, IOException {
        return anggotDao.exportPdfFile();
    }
}
