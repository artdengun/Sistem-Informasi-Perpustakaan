package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.Peminjaman;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.AnggotaService;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.BukuService;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PeminjamanController {

    @Autowired  private PeminjamanService peminjamanService;
    @Autowired private AnggotaService anggotaService;
    @Autowired private BukuService bukuService;

    @GetMapping("peminjaman")
    public String getPeminjaman(Model model){
        model.addAttribute("peminjamans", peminjamanService.getPeminjamanList());
        model.addAttribute("anggotas", anggotaService.getAnggotaList());
        model.addAttribute("bukus", bukuService.getBukuList());

        return "Peminjaman";
    }

    @PostMapping("peminjaman/tambahData")
    public String tambahPeminjaman(Peminjaman peminjaman){
        peminjamanService.save(peminjaman);
        return "redirect:/peminjaman";
    }

    @RequestMapping("peminjaman/findById")
    @ResponseBody
    public Optional<Peminjaman> findById(String id){
        return peminjamanService.findById(id);
    }

    @RequestMapping(value = "peminjaman/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Peminjaman peminjaman){
        peminjamanService.save(peminjaman);

        return "redirect:/peminjaman";
    }

    @RequestMapping(value = "peminjaman/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        peminjamanService.delete(id);

        return "redirect:/peminjaman";
    }

}
