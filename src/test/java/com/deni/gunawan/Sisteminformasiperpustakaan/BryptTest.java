package com.deni.gunawan.Sisteminformasiperpustakaan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BryptTest {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testgenereatePasswordBcrypt(){
        String passwordMahasiswa = "mahasiswa";
        String bcryptStaff = passwordEncoder.encode(passwordMahasiswa);
        Assert.assertNotNull(bcryptStaff);
        System.out.println("mahasiswa : " + bcryptStaff);

        String passwordAdmin = "admin";
        String bcryptAdmin = passwordEncoder.encode(passwordAdmin);
        Assert.assertNotNull(passwordAdmin);
        System.out.println("admin : "+bcryptAdmin);


        String passwordDeveloper = "developer";
        String bcryptDeveloper = passwordEncoder.encode(passwordDeveloper);
        Assert.assertNotNull(passwordDeveloper);
        System.out.println("developer : "+bcryptDeveloper);
    }

}
