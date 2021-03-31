package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class ApplicationController {

    /*  Default Application Controller */
    @GetMapping("dashboard")
    public String getDashboard(){
        return "dashboard";
    }

    @GetMapping("register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("forgot-password")
    public String getForgotPassword(){
        return "forgot-password";
    }

    @GetMapping("blank")
    public String getBlank(){
        return "blank";
    }

    @GetMapping("table")
    public String getTables(){
        return "tables";
    }

    @GetMapping({"/", "login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("404")
    public String getError(){
        return "404";
    }


}
