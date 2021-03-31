package com.deni.gunawan.Sisteminformasiperpustakaan.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);

}
