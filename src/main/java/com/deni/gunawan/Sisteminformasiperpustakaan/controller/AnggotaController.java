package com.deni.gunawan.Sisteminformasiperpustakaan.controller;


import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.AnggotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Optional;

@Controller
public class AnggotaController {

    @Autowired  private AnggotaService anggotaService;

    @GetMapping("anggota")
    public String getAnggota(Model model){
        List<Anggota> anggotaList = anggotaService.getAnggotaList();
        model.addAttribute("anggotas", anggotaList);

        return "Anggota";
    }

    @PostMapping("anggota/tambahData")
    public String tambahAnggota(Anggota anggota){
        anggotaService.save(anggota);
        return "redirect:/anggota";
    }

    @RequestMapping("anggota/findById")
    @ResponseBody
    public Optional<Anggota> findById(String id){
        return anggotaService.findById(id);
    }

    @RequestMapping(value = "anggota/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Anggota anggota){
        anggotaService.save(anggota);
        return "redirect:/anggota";
    }

    @RequestMapping(value = "anggota/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        anggotaService.delete(id);
        return "redirect:/anggota";
    }


}


