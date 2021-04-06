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
        String passwordUser = "passworduser";
        String bcryptUser = passwordEncoder.encode(passwordUser);
        Assert.assertNotNull(bcryptUser);
        System.out.println("user = " + bcryptUser);

        String passwordStaff = "passwordstaff";
        String bcryptStaff = passwordEncoder.encode(passwordStaff);
        Assert.assertNotNull(bcryptStaff);
        System.out.println("staff = "+bcryptStaff);


        String passwordAdmin = "admin";
        String bcryptAdmin = passwordEncoder.encode(passwordAdmin);
        Assert.assertNotNull(bcryptAdmin);
        System.out.println("admin =  "+bcryptAdmin);
    }

}
