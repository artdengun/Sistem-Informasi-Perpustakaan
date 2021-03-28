package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Pengembalian;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.PengembalianService;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PengembalianController {

    @Autowired private PengembalianService pengembalianService;
    @Autowired private PeminjamanService peminjamanService;

    @GetMapping("pengembalian")
    public String getPengembalian(Model model){
        model.addAttribute("pengembalians", pengembalianService.getPengembalianList());
        model.addAttribute("peminjamans", peminjamanService.getPeminjamanList());

        return "Pengembalian";
    }

    @PostMapping("pengembalian/{id}")
    public String tambahData(Pengembalian pengembalian){
        pengembalianService.save(pengembalian);
        return "redirect:/pengembalian";
    }

    @RequestMapping("pengembalian/{id}")
    @ResponseBody
    public Optional<Pengembalian> findById(String id){
        return pengembalianService.findById(id);
    }

    @RequestMapping(value = "pengembalian/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Pengembalian pengembalian){
        pengembalianService.save(pengembalian);
        return "redirect:/pengembalian";
    }

    @RequestMapping(value = "pengembalian/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        pengembalianService.delete(id);
        return "redirect:/pengembalian";
    }

}
