package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BukuController {

    @Autowired  private BukuService bukuService;

    @GetMapping("buku")
    public String getBuku(Model model){
        List<Buku> bukuList = bukuService.getBukuList();
        model.addAttribute("bukus", bukuList);

        return "Buku";
    }

    @PostMapping("buku/tambahData")
    public String tambahData(Buku Buku){
        bukuService.save(Buku);
        return "redirect:/buku";
    }

    @RequestMapping("buku/findById")
    @ResponseBody
    public Optional<Buku> findById(String id){
        return bukuService.findById(id);
    }

    @RequestMapping(value = "buku/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Buku Buku){
        bukuService.save(Buku);
        return "redirect:/buku";
    }

    @RequestMapping(value = "buku/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        bukuService.delete(id);
        return "redirect:/buku";
    }

}
