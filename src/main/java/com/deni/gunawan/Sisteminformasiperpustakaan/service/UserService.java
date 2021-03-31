package com.deni.gunawan.Sisteminformasiperpustakaan.service;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listUser();

    void save(User user);

    void updateStatus(User user);

    User findByEmail(String email);

    Optional<User> findById(String id);
}
