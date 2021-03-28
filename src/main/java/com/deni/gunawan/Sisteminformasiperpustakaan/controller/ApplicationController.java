package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class ApplicationController {

    /*  Default Application Controller */
    @GetMapping("dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("blank")
    public String tables(){
        return "blank";
    }

    @GetMapping("table")
    public String gettables(){
        return "tables";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

//    @GetMapping("404")
//    public String error(){
//        return "404";
//    }


}
