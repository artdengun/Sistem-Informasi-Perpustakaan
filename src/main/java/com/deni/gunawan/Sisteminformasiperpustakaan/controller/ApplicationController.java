package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @GetMapping("dashboard")
    public String getDashboard(){
            return "dashboard";
    }

    @GetMapping("signin")
    public String getSignIn(){
        return "signin";
    }

    @GetMapping("signup")
    public String getSignUp(){
        return "signup";
    }

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @PostMapping("authentication")
    public String getSuccessLogin(){
        return "dashboard";
    }


}
