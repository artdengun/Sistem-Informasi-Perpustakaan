package com.deni.gunawan.Sisteminformasiperpustakaan.configuration;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.User;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("user tidak ada");
        }
        return new CustomUserDetails(user);
    }

}
