package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AnggotaRepository;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.UserRepository;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.AnggotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class LogUserController {

    @Autowired
    AnggotaRepository anggotaRepository;
    @Autowired UserRepository userRepository;

    @GetMapping("/switch/anggota/list")
    public ModelMap daftarAnggota(Authentication currentUser){
        ModelMap anggota = new ModelMap();

        userRepository.findByUsername(currentUser.getName())
                .ifPresent(p -> {
                    anggota.addAttribute("daftarAnggota",
                            anggotaRepository.findByUser(p));
                });

        return anggota;
    }


}


