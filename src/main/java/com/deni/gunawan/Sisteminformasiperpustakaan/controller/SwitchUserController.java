package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AuditLogRepository;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwitchUserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogRepository auditLogDao;

    @GetMapping("/switchuser/auditlog")
    public ModelMap daftarKegiatan() {
        return new ModelMap()
                .addAttribute("daftarAuditLog",
                        auditLogDao.findAll());
    }

    @GetMapping("/switchuser/select")
    public ModelMap pilihUser() {
        return new ModelMap()
                .addAttribute("daftarUser",
                        userRepository.findAll());
    }
}
