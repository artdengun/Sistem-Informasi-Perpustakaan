package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AnggotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AnggotaController {

    @Autowired  private AnggotaRepository anggotaService;
    
    @GetMapping("anggota")
    public String getAnggota(Model model){
        List<Anggota> AnggotaList = anggotaService.findAll();
        model.addAttribute("anggotas", AnggotaList);

        return "Anggota";
    }

    @PostMapping("anggota/tambahData")
    public String tambahData(Anggota anggota){

        anggotaService.save(anggota);
        return "redirect:/anggota";
    }

    @RequestMapping("anggota/findById")
    @ResponseBody
    public Optional<Anggota> findById(String id){
        if (id != null && !id.trim().equals("") && id.equalsIgnoreCase("OK")) {
            return anggotaService.findById(id);
        }
        return null;
    }

    @RequestMapping(value = "anggota/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Anggota anggota){
        anggotaService.save(anggota);
        return "redirect:/anggota";
    }

    @RequestMapping(value = "anggota/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        anggotaService.deleteById(id);
        return "redirect:/anggota";
    }

}
