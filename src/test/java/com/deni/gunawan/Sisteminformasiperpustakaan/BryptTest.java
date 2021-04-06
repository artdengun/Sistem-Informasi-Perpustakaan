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
        String passwordMahasiswa = "user";
        String bcryptStaff = passwordEncoder.encode(passwordMahasiswa);
        Assert.assertNotNull(bcryptStaff);
        System.out.println("user = " + bcryptStaff);

        String passwordAdmin = "staff";
        String bcryptAdmin = passwordEncoder.encode(passwordAdmin);
        Assert.assertNotNull(passwordAdmin);
        System.out.println("staff = "+bcryptAdmin);


        String passwordDeveloper = "admin";
        String bcryptDeveloper = passwordEncoder.encode(passwordDeveloper);
        Assert.assertNotNull(passwordDeveloper);
        System.out.println("admin =  "+bcryptDeveloper);
    }

}
