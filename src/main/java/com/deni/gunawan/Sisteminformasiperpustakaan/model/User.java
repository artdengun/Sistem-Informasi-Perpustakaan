package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty @Max(50)
    private String firstname;
    @NotNull @NotEmpty @Max(50)
    private String lastname;
    @NotNull @NotEmpty @Max(50)
    private String username;
    @NotNull @NotEmpty @Max(150)
    private String email;
    @NotNull @NotEmpty @Max(150)
    private String password;

    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "s_user_permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    private String roleid;
}
