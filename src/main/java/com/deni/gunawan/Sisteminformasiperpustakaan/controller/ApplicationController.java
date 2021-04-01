package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ApplicationController {

    @GetMapping("dashboard")
    public String getDashboard(){
        return "dashboard";
    }
}
